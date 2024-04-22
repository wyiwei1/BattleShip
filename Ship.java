package battleship;

public abstract class Ship {
	
	private int bowRow;// The row that contains the bow(front part of the ship)
	
	private int bowColumn; //The column that contains the bow(front part of the ship)
	
	private int length;// The length of the ship
	
	private boolean horizontal; // A boolean that represents whether the ship is going to be placed horizontally or vertically
	
	private boolean[] hit; // whether that part of the ship has been hit or not
	
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];
		for(int i =0 ;i<length; i++) {
			hit[i] = false;
		}
	}
	
	// Getters
	public int getLength() {
		return length;
	}
	
	public int getBowRow() {
		return bowRow;
	}
	
	public int getBowColumn() {
		return bowColumn;
	}
	
	public boolean[] getHit() {
		return hit;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
	
	//Setters
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	// Abstract methods
	public abstract String getShipType();
	
	// Other methods
	/*
	 * returns true if it is okay to put a ship of this length with its bow in this location
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		for(int i = length; i > 0; i--) {
	        // Check if it is in the given area
	        if(column < 0 || row < 0 || column > 9 || row > 9) {
	            return false;
	        }
	        // Check if the location is occupied
	        for(int dr = -1; dr <= 1; dr++) {
	            for(int dc = -1; dc <= 1; dc++) {
	                int checkRow = row + dr;
	                int checkColumn = column + dc;
	                // check if it is out of the area
	                if(checkRow >= 0 && checkRow <= 9 && checkColumn >= 0 && checkColumn <= 9) {
	                    if(ocean.isOccupied(checkRow, checkColumn)) {
	                        return false;
	                    }
	                }
	            }
	        }
	        // Update the index of the row or column based on ship direction
	        if(horizontal) {
	            column--;
	        } else {
	            row--;
	        }
	    }
	    return true;
	}
	
	/*
	 * puts the ship in the ocean
	 */

	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	    // Set the ship's position and orientation
	    setBowRow(row);
	    setBowColumn(column);
	    setHorizontal(horizontal);

	    // Place the ship in the ocean array
	    for (int i = 0; i < getLength(); i++) {
	        if (this.horizontal) {
	            // For horizontal placement, the bow is to the right, so decrement column as you go left
            
	        	ocean.placeShips(this, row, column-i);
	            
	        } else {
	            // For vertical placement, the bow is at the bottom, so decrement row as you go up

	            ocean.placeShips(this, row-i, column);

	        }
	    }
	}

	boolean shootAt(int row, int column) {
		// First check if the location of the shot belongs to the position of the boat
	    // If the boat is placed horizontally, the rows should match and be listed between the bow row and the bow row minus the length
	    // If the boat is placed vertically, the columns should match and the rows are between the bow row and the bow row minus the length
	    if ((this.horizontal && row == this.bowRow && column <= this.bowColumn && column > this.bowColumn - length) ||
	        (!this.horizontal && column == this.bowColumn && row <= this.bowRow && row > this.bowRow - length)) {
	        // Calculate the offset of the firing position relative to the bow
	        int offset = this.horizontal ?  this.bowColumn - column : this.bowRow- row;

	        // If this part has already been hit, hitting the same spot again returns true
	        if (hit[offset]) {
	            return true;
	        }

	        // Otherwise mark this part as hit
	        hit[offset] = true;

	        // If this part is now hit, return true
	        return true;
	    }

	    // If the firing position is not in the occupied position of the ship, return false
	    return false;
		
	}
	
	//just check if the part of the ship was shot

	
	boolean ifShootAt(int row, int column) {
	    // First check if the location of the shot belongs to the position of the boat
	    if ((this.horizontal && row == this.bowRow && column <= this.bowColumn && column > this.bowColumn - length) ||
	        (!this.horizontal && column == this.bowColumn && row <= this.bowRow && row > this.bowRow - length)) {
	        // Calculate the offset of the firing position relative to the bow
	        int offset = this.horizontal ?  this.bowColumn - column : this.bowRow- row;

	        // Return true if this part has already been hit, indicating the shot is a hit
	        // Note: We're no longer changing the state of the hit array here
	        return hit[offset];
	    }
	    // If the firing position is not within the occupied position of the ship, return false
	    return false;
	}

	/*
	 * return true if every part of ship has been hit, false otherwise
	 */
	boolean isSunk() {
		for (boolean b : hit) {
            if (!b) {
                return false;
            }
        }
        return true;
	}
	
	/*
	 * return a single-character String to use in the Ocean's print method. "s" if 
	 * the ship has been sunk and "x" if it has not been sunk.
	 */
	@Override
	public String toString() {
		if(isSunk()) {
			return "s";
		}
		else {
			return "x";
		}
	}

}
	




























