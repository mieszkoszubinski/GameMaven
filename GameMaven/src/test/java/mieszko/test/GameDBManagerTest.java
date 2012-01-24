package mieszko.test;

import static org.junit.Assert.*;

import mieszko.project.*;
import mieszko.services.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GameDBManagerTest {

	GameDBManager dbGame = new GameDBManager();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbGame.addGame(new Game("Battlefield 3", GameType.Shooter, 2011, 120));
	}

	@After
	public void tearDown() throws Exception {
		dbGame.deleteAllGame();
	}

	@Test
	public void testAddGame() {
		dbGame.addGame(new Game("Battlefield 1942", GameType.Shooter, 2002, 40));
		dbGame.addGame(new Game("Battlefield Vietnam", GameType.Shooter, 2004, 40));
		assertEquals(3, dbGame.getAllGames().size());
	}

	@Test
	public void testGetAllGames() {
		dbGame.addGame(new Game("Shogun 2", GameType.Action, 2008, 40));
		assertEquals(2, dbGame.getAllGames().size());
	}

	@Test
	public void testDeleteAllGame() {
		dbGame.addGame(new Game("Shogun 2", GameType.Action, 2008, 40));
		dbGame.deleteAllGame();
		assertEquals(0, dbGame.getAllGames().size());
		assertTrue(dbGame.getAllGames().size() == 0);
	}

	@Test
	public void testFindGameByName() {
		dbGame.addGame(new Game("Shogun 2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("Call of Duty 3", GameType.Action, 2008, 40));
		assertTrue(dbGame.findGameByName("Shogun").size() == 1);
	}

	@Test
	public void testFindGameByType() {
		dbGame.addGame(new Game("Shogun 2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("Call of Duty 3", GameType.Racing, 2008, 40));
		assertEquals(2, dbGame.findGameByType(GameType.Action).size());
	}

	@Test
	public void testDeleteGame() {
		dbGame.addGame(new Game("Shogun 2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("Call of Duty 3", GameType.Racing, 2008, 40));
		dbGame.deleteGame(dbGame.findGameByName("Call of Duty 3"));
		assertEquals(2, dbGame.getAllGames().size());	
	}

}
