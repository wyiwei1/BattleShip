package battleship;

public class Submarine extends Ship{
	static final int LENGTH = 1;
	
	public Submarine(){
		super(LENGTH);
		
	}

	@Override
	public String getShipType() {
		return "submarine";
	}
}
