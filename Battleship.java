package battleship;

public class Battleship extends Ship{
	static final int LENGTH = 4;
	public Battleship() {
		super(LENGTH);
	}
	@Override
	public String getShipType() {
		
		return "battleship";
	}
}