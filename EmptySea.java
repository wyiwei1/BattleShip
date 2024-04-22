package battleship;

public class EmptySea extends Ship{
	
	static final int LENGTH = 1;
	public EmptySea() {
		
		super(LENGTH);
	}

	@Override
	boolean shootAt(int row, int column) {
		//Set hit[0] for EmptySea to true to indicate that it was hit
		boolean[] hit = new boolean[1];
		hit[0] = true;
		setHit(hit);
		return false;
	}
	
	@Override
	boolean isSunk() {
		return false;
	}
	
	@Override
	/*
	 * return the single-character "-" String to use in the Ocean's print method
	 */
	public String toString() {
		return "-";
	}
	
	@Override
	public String getShipType() {
		return "empty";
	}

}
