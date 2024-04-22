package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		// get the length of cruiser
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		// get the length of destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		// get the length of submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		// get the length of empty sea
		ship = new EmptySea();
		assertEquals(1, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		// test when the direction is vertical and the ship is cruiser
		Ship cruiser = new Cruiser();
		int row2 = 4;
		int column2 = 2;
		boolean horizontal2 = false;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2, cruiser.getBowRow());
		
		// test when the direction is vertical and the ship is cruiser
		Ship destroyer = new Destroyer();
		int row3 = 8;
		int column3 = 7;
		boolean horizontal3 = false;
		destroyer.placeShipAt(row3, column3, horizontal3, ocean);
		assertEquals(row3, destroyer.getBowRow());
		
		// test when the direction is horizontal and the ship is submarine
		Ship submarine = new Submarine();
		int row4 = 6;
		int column4 = 4;
		boolean horizontal4 = false;
		submarine.placeShipAt(row4, column4, horizontal4, ocean);
		assertEquals(row4, submarine.getBowRow());
		// Test when the ship is an empty sea
		Ship emptysea = new EmptySea();
		row4 = 9;
		column4 = 0;
		horizontal4 = false;
		emptysea.placeShipAt(row4, column4, horizontal4, ocean);
		assertEquals(row4, emptysea.getBowRow());

	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		// test when the direction is vertical and the ship is cruiser
		Ship cruiser = new Cruiser();
		int row2 = 4;
		int column2 = 2;
		boolean horizontal2 = false;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		cruiser.setBowColumn(column2);
		assertEquals(column2, cruiser.getBowColumn());
		
		// test when the direction is vertical and the ship is cruiser
		Ship destroyer = new Destroyer();
		int row3 = 8;
		int column3 = 7;
		boolean horizontal3 = false;
		destroyer.placeShipAt(row3, column3, horizontal3, ocean);
		destroyer.setBowColumn(column3);
		assertEquals(column3, destroyer.getBowColumn());
		
		// test when the direction is horizontal and the ship is submarine
		Ship submarine = new Submarine();
		int row4 = 6;
		int column4 = 4;
		boolean horizontal4 = false;
		submarine.placeShipAt(row4, column4, horizontal4, ocean);
		submarine.setBowColumn(column4);
		assertEquals(column4, submarine.getBowColumn());

		// test when the direction is horizontal and the ship is empty sea
		Ship emptysea = new EmptySea();
		row4 = 9;
		column4 = 0;
		horizontal4 = false;
		emptysea.placeShipAt(row4, column4, horizontal4, ocean);
		emptysea.setBowColumn(column4);
		assertEquals(column4, emptysea.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		// Test the remaining part of the ship
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		//Test when the ship was shoot
		ship.placeShipAt(4, 6, true, ocean);
		ship.shootAt(4, 6);
		assertTrue(ship.getHit()[0]);
		
		// Test specific part was hit
		ship.shootAt(4, 4);
		assertTrue(ship.getHit()[2]);
		
		// Test when the ship was sunk
		ship.shootAt(4, 5);
		ship.shootAt(4, 3);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		assertTrue(ship.getHit()[3]);
		
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		// test other type of ship
		Ship ship2 = new Cruiser();
		assertEquals("cruiser", ship2.getShipType());
		
		Ship ship3 = new Destroyer();
		assertEquals("destroyer", ship3.getShipType());
		
		Ship ship4 = new Submarine();
		assertEquals("submarine", ship4.getShipType());

	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		
		// Test when the battleship is vertical
		Ship battleship2 = new Battleship();
		int row2 = 6;
		int column2 = 6;
		boolean horizontal2 = false;
		battleship.placeShipAt(row2, column2, horizontal2, ocean);
		assertFalse(battleship2.isHorizontal());
		
		// Test when a cruiser is horizontal
		Ship cruiser = new Cruiser();
		int row3 = 7;
		int column3 = 7;
		boolean horizontal3 = true;
		cruiser.placeShipAt(row3, column3, horizontal3, ocean);
		assertTrue(cruiser.isHorizontal());
		
		// Test when a submarine is vertical
		Ship submarine = new Submarine();
		int row4 = 8;
		int column4 = 8;
		boolean horizontal4 = false;
		submarine.placeShipAt(row4, column4, horizontal4, ocean);
		assertFalse(submarine.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		//cruiser
		Ship cruiser = new Cruiser();
		int row1 = 6;
		int column1 = 7;
		boolean horizontal1 = false;
		cruiser.setBowRow(row1);
		assertEquals(row1, cruiser.getBowRow());
		
		//destroyer
		Ship destroyer = new Destroyer();
		int row2 = 7;
		int column2 = 8;
		boolean horizontal2 = true;
		destroyer.setBowRow(row2);
		assertEquals(row2, destroyer.getBowRow());
		
		//submarine
		Ship submarine = new Submarine();
		int row3 = 8;
		int column3 = 9;
		boolean horizontal3 = false;
		submarine.setBowRow(row3);
		assertEquals(row3, submarine.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		
		//cruiser
		Ship cruiser = new Cruiser();
		int row1 = 6;
		int column1 = 7;
		boolean horizontal1 = false;
		cruiser.setBowColumn(column1);
		assertEquals(column1, cruiser.getBowColumn());
		
		//destroyer
		Ship destroyer = new Destroyer();
		int row2 = 7;
		int column2 = 8;
		boolean horizontal2 = true;
		destroyer.setBowColumn(column2);
		assertEquals(column2, destroyer.getBowColumn());
		
		//submarine
		Ship submarine = new Submarine();
		int row3 = 8;
		int column3 = 9;
		boolean horizontal3 = false;
		submarine.setBowColumn(column3);
		assertEquals(column3, submarine.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		
		// test when set the battleship vertical
		Ship battleship2 = new Battleship();
		int row2 = 2;
		int column2 = 5;
		boolean horizontal2 = false;
		battleship2.setHorizontal(horizontal2);
		assertFalse(battleship2.isHorizontal());
		
		// test when set a destroyer horizontal
		Ship destroyer = new Destroyer();
		int row3 = 3;
		int column3 = 6;
		boolean horizontal3 = true;
		destroyer.setHorizontal(horizontal3);
		assertTrue(destroyer.isHorizontal());
		
		// test when set a submarine vertical
		Ship submarine = new Submarine();
		int row4 = 5;
		int column4 = 8;
		boolean horizontal4 = false;
		submarine.setHorizontal(horizontal4);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		//Change a type of ship and direction
		Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = false;
		ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// The ship overshot the left or right boundary 
		int row5 = 5;
		int column5 = 1;
		boolean horizontal5 = true;
		boolean ok5 = cruiser.okToPlaceShipAt(row5, column5, horizontal5, ocean);
		assertFalse(ok5, "can not place ship here.");
		int row5_1 = 5;
		int column5_1 = 10;
		boolean horizontal5_1 = true;
		boolean ok5_1 = cruiser.okToPlaceShipAt(row5_1, column5_1, horizontal5_1, ocean);
		assertFalse(ok5_1, "can not place ship here.");
		
		// The ship overshot the top or the bottom boundary
		int row6 = 1;
		int column6 = 7;
		boolean horizontal6 = false;
		boolean ok6 = cruiser.okToPlaceShipAt(row6, column6, horizontal6, ocean);
		assertFalse(ok6, "can not place ship here.");
		int row6_1 = 10;
		int column6_1 = 10;
		boolean horizontal6_1 = false;
		boolean ok6_1 = cruiser.okToPlaceShipAt(row6_1, column6_1, horizontal6_1, ocean);
		assertFalse(ok6_1, "can not place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		// The ship being placed overlaps with the one already in the ocean

		Ship battleship2_1 = new Battleship();
		int row2 = 3;
		int column2 = 2;
		boolean horizontal2 = false;
		boolean ok2_1 = battleship2_1.okToPlaceShipAt(row2, column2, horizontal2, ocean);
		assertFalse(ok2_1, "can not place ship here.");
		// The ship that is being placed is in contact with the ship that already exists
		Ship cruiser = new Cruiser();
		int row3 = 3;
		int column3 = 4;
		int row3_1 = 4;
		boolean horizontal3 = false;
		boolean ok3 = cruiser.okToPlaceShipAt(row3, column3, horizontal3, ocean);
		assertFalse(ok3, "can not place ship here.");
		// change the location of the ship, check if the ship can be placed
		ok3 = cruiser.okToPlaceShipAt(row3_1, column3, horizontal3, ocean);
		assertTrue(ok3, "OK to place ship here.");
		
		// contact with the existing ship diagonally
		int row4 = 3;
		int column4 = 5;
		boolean horizontal4 = false;
		boolean ok4 = cruiser.okToPlaceShipAt(row4, column4, horizontal4, ocean);
		assertFalse(ok4, "can not place ship here.");
		
		// The ship overshot the left or right boundary 
		int row5 = 5;
		int column5 = 1;
		boolean horizontal5 = true;
		boolean ok5 = cruiser.okToPlaceShipAt(row5, column5, horizontal5, ocean);
		assertFalse(ok5, "can not place ship here.");
		int row5_1 = 5;
		int column5_1 = 10;
		boolean horizontal5_1 = true;
		boolean ok5_1 = cruiser.okToPlaceShipAt(row5_1, column5_1, horizontal5_1, ocean);
		assertFalse(ok5_1, "can not place ship here.");
		
		// The ship overshot the top or the bottom boundary
		int row6 = 1;
		int column6 = 7;
		boolean horizontal6 = false;
		boolean ok6 = cruiser.okToPlaceShipAt(row6, column6, horizontal6, ocean);
		assertFalse(ok6, "can not place ship here.");
		int row6_1 = 10;
		int column6_1 = 10;
		boolean horizontal6_1 = false;
		boolean ok6_1 = cruiser.okToPlaceShipAt(row6_1, column6_1, horizontal6_1, ocean);
		assertFalse(ok6_1, "can not place ship here.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		// cruiser, vertical
		Ship cruier = new Cruiser();
		 row = 5;
		 column = 5;
		 horizontal = false;
		cruier.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruier.getBowRow());
		assertEquals(column, cruier.getBowColumn());
		assertFalse(cruier.isHorizontal());
		assertEquals(cruier, ocean.getShipArray()[5][5]);
		assertEquals(cruier, ocean.getShipArray()[4][5]);
		assertEquals(cruier, ocean.getShipArray()[3][5]);
		assertEquals("empty", ocean.getShipArray()[2][5].getShipType());
		
		//destroyer, horizontal
		Ship destroyer = new Destroyer();
		 row = 6;
		 column = 8;
		 horizontal = true;
		 destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());
		assertEquals(destroyer, ocean.getShipArray()[6][8]);
		assertEquals(destroyer, ocean.getShipArray()[6][7]);
		assertEquals("empty", ocean.getShipArray()[6][6].getShipType());
		
		//submarine, horizontal
		Ship submarine = new Submarine();
		 row = 7;
		 column = 4;
		 horizontal = true;
		 submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertTrue(submarine.isHorizontal());
		assertEquals(submarine, ocean.getShipArray()[7][4]);

	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		
		// shoot at the row
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		// shoot at the second part
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		
		// shoot at the third part
		assertTrue(battleship.shootAt(0, 7));
		boolean[] hitArray3 = {true, true, true, false};
		assertArrayEquals(hitArray3, battleship.getHit());
		
		// shoot at the final part
		assertTrue(battleship.shootAt(0, 6));
		boolean[] hitArray4 = {true, true, true, true};
		assertArrayEquals(hitArray4, battleship.getHit());
		
		// shoot at the repeating part(final part)
		assertTrue(battleship.shootAt(0, 6));
		assertArrayEquals(hitArray4, battleship.getHit());
		
		//test if the battleship is sunk
		assertTrue(battleship.isSunk());


	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		//destroyer
		Destroyer destroyer = new Destroyer();
		int row2 = 4;
		int column2 = 5;
		boolean horizontal2 = true;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(4, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(4, 4));
		assertTrue(destroyer.isSunk());
		
		Cruiser cruiser = new Cruiser();
		row2 = 7;
		column2 = 7;
		horizontal2 = false;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(7, 7));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(6, 7));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(5, 7));
		assertTrue(cruiser.isSunk());
		
		//battleship
		Battleship battleship = new Battleship();
		row2 = 9;
		column2 = 2;
		horizontal2 = false;
		battleship.placeShipAt(row2, column2, horizontal2, ocean);
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(9, 2));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(8, 2));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(7, 2));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(6, 2));
		assertTrue(battleship.isSunk());
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		//test when the battleship was sunk
		battleship.shootAt(8, 1);
		assertEquals("x", battleship.toString());
		battleship.shootAt(7, 1);
		assertEquals("x", battleship.toString());
		battleship.shootAt(6, 1);
		assertEquals("s", battleship.toString());
		
	}

}
