package battleship;
import java.util.Scanner;
public class BattleshipGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Battleship game ");

		boolean ifPlay = true;
		
		
		while(ifPlay) {
			int sunkShip =0;
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			
	        // determine if the game is over
			while(!ocean.isGameOver()) {
				
//			ocean.printWithShips();//This line of code is used to cheat
			
				ocean.print();	
				int row = 0, column = 0;
				boolean isValidInput = false;
				while (!isValidInput) {
					String inputLine = scanner.nextLine(); // Read the entire line of input
					String[] parts = inputLine.split("\\D+"); // Use non-numeric characters to split the input

					// Check to see if you get at least two numbers after splitting
					if (parts.length >= 2) {
						try {
							row = Integer.parseInt(parts[0]);
							column = Integer.parseInt(parts[1]);

							// Verify that rows and columns are in the range 0 to 9
							if (row >= 0 && row <= 9 && column >= 0 && column <= 9) {
								isValidInput = true; // Input valid
							} else {
								// If the row or column is out of range, prompt the user to re-enter
								System.out.println("Row or column is out of the allowed range (0-9). Please re-enter:");
							}
						} catch (NumberFormatException e) {
							// If the conversion fails, an exception is caught and the user is prompted to re-enter
							System.out.println("Invalid input. Please make sure to enter two integers separated by any non-numeric character(s):");
						}
					} else {
						System.out.println("Invalid input. Please make sure to enter two integers separated by any non-numeric character(s):");
					}
				}

		        
				// shoot at the given coordinate
				ocean.shootAt(row,column);
				System.out.println("Shot count: " + ocean.getShotsFired());
				System.out.println("Hit count: " + ocean.getHitCount());
				System.out.println("The number of sunk ships: "+ ocean.getShipsSunk());
				// When sinking a ship, the type of ship that was sunk is displayed
				if(ocean.getShipsSunk()> sunkShip) {
					if(ocean.getShipType(row, column).equals("battleship"))
						System.out.println("You successfully sunk a battleship");
					if(ocean.getShipType(row, column).equals("cruiser"))
						System.out.println("You successfully sunk a cruiser");
					if(ocean.getShipType(row, column).equals("destroyer"))
						System.out.println("You successfully sunk a destroyer");
					if(ocean.getShipType(row, column).equals("submarine"))
						System.out.println("You successfully sunk a submarine");
					sunkShip++;
				}
				System.out.println();
			}
			
			// destroyed all the ships
			System.out.println("You destroyed all the ships!");
			System.out.println("The total Shot count: " + ocean.getShotsFired());
			System.out.println("The total Hit count: " + ocean.getHitCount());
	
					
			//ask if the player want to play again
			System.out.println("Do you want to play again?(y/n)");

			while(true) {
			    String answer = scanner.next();
			    scanner.nextLine(); 
			    char firstChar = answer.charAt(0);
		        if (Character.isUpperCase(firstChar)) {
		            firstChar = Character.toLowerCase(firstChar);
		            }
		        if(firstChar == 'y') {
		    	    ifPlay = true;
		    	    break;
		        }
		        else if(firstChar == 'n') {
		    	    ifPlay = false;
		    	    break;
		        }
		        else {
		    	    System.out.println("Please enter yes or no.");
		        }
			}
		}
		// exit the game
		System.out.println("Exit the game");
    scanner.close();
	}

}
