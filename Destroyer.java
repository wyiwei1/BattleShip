package battleship;

public class Destroyer extends Ship{
	static final int LENGTH = 2;
	
	public Destroyer() {
		super(LENGTH);
	}

	@Override
	public String getShipType() {
		
		return "destroyer";
	}

}
