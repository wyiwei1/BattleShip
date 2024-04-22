package battleship;

public class Cruiser extends Ship{
	static final int LENGTH = 3;
	
	public Cruiser() {
		super(LENGTH);
	}

	@Override
	public String getShipType() {
		
		return "cruiser";
	}

}
