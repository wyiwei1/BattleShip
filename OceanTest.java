package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		//Test the remain part of destroyer
		assertTrue(ocean.isOccupied(0, 5));
		
		//Test the submarine
		assertTrue(ocean.isOccupied(0, 0));
		
		//Test other place
		assertFalse(ocean.isOccupied(0, 1));
		assertFalse(ocean.isOccupied(6, 7));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests
		//test if the destroyer is sunk
		assertTrue(destroyer.isSunk());
		
		//test an empty sea
		assertFalse(ocean.shootAt(7, 7));
		
		// test another type of ship
		Submarine submarine = new Submarine();
		row = 6;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(6, 6));
		assertTrue(submarine.isSunk());

	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		// Once a ship has been sunk, additional shots at its location should return false.
		assertFalse(ocean.shootAt(1, 5));
		assertFalse(ocean.shootAt(0, 5));

		// try to shoot at the submarine
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(9, ocean.getShotsFired());
		
		// try to shoot a ship's same part 
		Battleship battleship = new Battleship();
		row = 7;
		column = 7;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(7, 7));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(7, 7));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(7, 7));
		assertEquals(12, ocean.getShotsFired());
		
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		// test the condition the player not hit the ship
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(1, ocean.getHitCount());
		
		//test the condition the player hit the same part
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		
		//test when the ship was sunk
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertFalse(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(3, ocean.getHitCount());
		
		// shoots another ship
		Destroyer destroyer2 = new Destroyer();
		 row = 7;
		 column = 8;
		 horizontal = true;
		destroyer2.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(7, 8));
		assertFalse(destroyer2.isSunk());
		assertEquals(4, ocean.getHitCount());
		
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		// shoot at the same part
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		// sunk the ship
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// sunk another ship
		Submarine submarine = new Submarine();
	    row = 7;
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(7, 7));
		assertTrue(submarine.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
		//shoot the sunk ship
		assertFalse(ocean.shootAt(7, 7));
		assertTrue(submarine.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
		
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		// place a ship
		Submarine submarine = new Submarine();
	    int row = 7;
		int column = 7;
		boolean horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		assertEquals("submarine", shipArray[7][7].getShipType());
		
		//Place another ship
		Battleship battleship = new Battleship();
	    row = 4;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		assertEquals("battleship", shipArray[4][4].getShipType());
		assertEquals("battleship", shipArray[4][3].getShipType());
		assertEquals("battleship", shipArray[4][2].getShipType());
		assertEquals("battleship", shipArray[4][1].getShipType());
	}

}
