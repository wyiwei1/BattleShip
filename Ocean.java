package battleship;
import java.util.Random;
// contains 10x10 array of Ships, representing an "Ocean", and some methods to manipulate it
public class Ocean {
	
	private Ship[][] Ships = new Ship[10][10];// Used to quickly determine which ship is in any given location
	
	private int shotsFired; // The total number of shots fired by the user
	
	private int hitCount; // the number of times a shot hit a ship
	
	private int shipSunk;
	
	public Ocean() {
		this.shotsFired = 0;
	    this.hitCount = 0;
	    this.shipSunk = 0;
	    fillEmptyOcean();
	}
	
	// methods
	
	/*
	 * Creates an empty ocean and fills the ships array with EmptySea objects
	 */
	private void fillEmptyOcean() {
		
		for(int i = 0; i<10;i++) {
			for(int j = 0; j<10; j++)
			{
				this.Ships[i][j] = new EmptySea();
				this.Ships[i][j].setBowRow(i);
				this.Ships[i][j].setBowColumn(j);
			}
		}
	}
	
	/*
	 * Place all ten ships randomly on the ocean.
	 */
	void placeAllShipsRandomly() {
	    Random rand = new Random();
	    
	    // Place different types of ships in sequence
	    placeShipsOfType(new Battleship(), 1, rand);
	    placeShipsOfType(new Cruiser(), 2, rand);
	    placeShipsOfType(new Destroyer(), 3, rand);
	    placeShipsOfType(new Submarine(), 4, rand);
	}
    // Place ships by type
	void placeShipsOfType(Ship shipPrototype, int count, Random rand) {
	    int placed = 0; // Number of ships placed
	    while (placed < count) {
	        // Randomly generate the starting position and direction
	        int row = rand.nextInt(10);
	        int column = rand.nextInt(10);
	        boolean horizontal = rand.nextBoolean();

	        // Attempt to place ships
	        Ship newShip;
	        try {
	            // Use reflection to create a new instance of the same type as shipPrototype
	            newShip = shipPrototype.getClass().getDeclaredConstructor().newInstance();
	        } catch (Exception e) {
	            // Handle the exceptions possibly thrown by reflection methods
	            throw new RuntimeException("Cannot create a new instance of ship", e);
	        }
	        
	        if (newShip.okToPlaceShipAt(row, column, horizontal, this)) {
	            newShip.placeShipAt(row, column, horizontal, this);
	            placed++; // Successfully placed the ship, increase the count
	        }
	    }
	}
	
	void placeShips(Ship ship, int row, int column) {
		this.Ships[row][column] = ship;
	}
	
	/*
	 * return true if the given location contains a ship, false if it does not 
	 */
	boolean isOccupied(int row, int column) {
	    // Check if the object at the given location is an instance of EmptySea
	    if (this.Ships[row][column] instanceof EmptySea) {
	        return false; // The location is not occupied by a ship
	    } else {
	        return true; // The location is occupied by a ship
	    }
	}
	
	boolean shootAt(int row, int column) {
	    this.shotsFired++;  // Always increase the number of shots

	    // Use instanceof to check whether the object in the Ships array is an instanceof the EmptySea class
	    if (!(this.Ships[row][column] instanceof EmptySea)&& !this.Ships[row][column].isSunk()) {
	        this.hitCount++;  // Increase hit count only if you hit a real ship
	        this.Ships[row][column].shootAt(row, column);
	        if(this.Ships[row][column].isSunk()) {
	        	this.shipSunk++;
	        }
	        return true;
	    }
	    else {
	    	if(this.Ships[row][column] instanceof EmptySea) {
	    		this.Ships[row][column].shootAt(row, column);
	    	}
	        return false;
	    }
	}

	
	/*
	 * returns the nunmber of shots fired(int the game)
	 */
	int getShotsFired() {
		return shotsFired;
	}
	
	/*
	 * returns the number of hits recorded(in the game). All hits are counted,
	 * not just the first time a given square is hit.
	 */
	int getHitCount() {
		return hitCount;
	}
	
	/*
	 * return the number of ships sunk
	 */
	int getShipsSunk() {
		return shipSunk;
	}
	
	/*
	 * returns true if all ships have been sunk, otherwise false
	 */
	boolean isGameOver() {
		if (getShipsSunk() == 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * returns the 10x10 array of Ships
	 */
	Ship[][] getShipArray(){
		return this.Ships;
	}
	
	/*
	 * prints the ocean
	 */
	void print() {
        EmptySea emptysea = new EmptySea();
		int[] num = new int[10];
		for(int i = 0;i<10; i++) {
			num[i] = i;
		}
		// fill all ocean with "." 
		String[][] ocean = new String[11][11];
		for (int i = 0; i < ocean.length; i++) {
		    for (int j = 0; j < ocean[i].length; j++) {
		        ocean[i][j] = ".";
		    }
		}		
		for(int i=0; i<=10;i++) {
			for(int j = 0; j<= 10; j++) {
				// nothing in the top left
				if(i==0&&j==0) {
			        ocean[i][j] = " ";
				}
				
				// row number and column number
				else if(i == 0) {
			        ocean[i][j] = Integer.toString(num[j-1]);
				}
				else if(j == 0) {
			        ocean[i][j] = Integer.toString(num[i-1]);
				}
				
				// ocean
				else {
					// not shot at
					if(!Ships[i-1][j-1].ifShootAt(i-1, j-1)) {
						ocean[i][j] = ".";
					}
					
					// shot at a ship or empty sea
					else {
						// shot at an empty sea
						if((this.Ships[i-1][j-1] instanceof EmptySea)) {
							ocean[i][j] = emptysea.toString();

						}
						// shot at a  ship
						else {
						    ocean[i][j] = Ships[i-1][j-1].toString();
						}
					}
				}
				
			}
		}
		// print the map
		for (int i = 0; i < ocean.length; i++) {
	        for (int j = 0; j < ocean[i].length; j++) {

	            System.out.print(ocean[i][j] + " "); 
	        }
	        System.out.println(); 
	    }
		System.out.println("Enter row, column: ");
	}
	
	/*
	 * use for debugging purposes only
	 */
	void printWithShips(){
		int[] num = new int[10];
		for(int i = 0;i<10; i++) {
			num[i] = i;
		}
		String[][] ocean = new String[11][11];
		for (int i = 0; i < ocean.length; i++) {
		    for (int j = 0; j < ocean[i].length; j++) {
		        ocean[i][j] = " ";
		    }
		}
		
		for(int i = 0;i< 11;i++) {
			for(int j = 0; j<11; j++) {
				// row number and column number
				if(i==0&&j==0) {
					ocean[i][j] = " ";
				}
				else if(i == 0) {
					ocean[i][j] = Integer.toString(num[j-1]);
				}
				else if(j == 0) {
					ocean[i][j] = Integer.toString(num[i-1]);
				}
				else {
					//set letters to represent different ships
					if(this.Ships[i-1][j-1] instanceof Battleship) {
						ocean[i][j] = "b";
					}
					else if (this.Ships[i-1][j-1] instanceof Cruiser) {
						ocean[i][j] = "c";
					}
					else if (this.Ships[i-1][j-1] instanceof Destroyer) {
						ocean[i][j] = "d";
					}
					else if (this.Ships[i-1][j-1] instanceof Submarine) {
						ocean[i][j] = "s";
					}
					else if (this.Ships[i-1][j-1] instanceof EmptySea) {

					}
					else {
						ocean[i][j] = " ";
					}
				}
			}
		}
		// print the map
		for (int i = 0; i < ocean.length; i++) {
	        for (int j = 0; j < ocean[i].length; j++) {

	            System.out.print(ocean[i][j] + " "); 
	        }
	        System.out.println(); 
	    }
     }
	
	String getShipType(int row,int column) {
		return Ships[row][column].getShipType();
	}


}


















