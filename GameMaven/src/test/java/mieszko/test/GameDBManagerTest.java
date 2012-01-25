package mieszko.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mieszko.services.*;
import mieszko.project.*;

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
		dbGame.addGame(new Game("LBP", GameType.Action, 2006, 40));
	}

	@After
	public void tearDown() throws Exception {
		dbGame.deleteAllGame();
	}

	@Test
	public void testAddGame() {
		dbGame.addGame(new Game("LBP2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("LBP3", GameType.Action, 2008, 40));
		assertEquals(3, dbGame.getAllGames().size());
	}

	@Test
	public void testGetAllGames() {
		dbGame.addGame(new Game("LBP2", GameType.Action, 2008, 40));
		assertEquals(2, dbGame.getAllGames().size());
	}

	@Test
	public void testDeleteAllGame() {
		dbGame.addGame(new Game("LBP2", GameType.Action, 2008, 40));
		dbGame.deleteAllGame();
		assertEquals(0, dbGame.getAllGames().size());
		assertTrue(dbGame.getAllGames().size() == 0);
	}

	@Test
	public void testFindGameByName() {
		dbGame.addGame(new Game("LBP2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("LBP3", GameType.Action, 2008, 40));
		assertTrue(dbGame.findGameByName("LBP").size() == 1);
	}

	@Test
	public void testFindGameByType() {
		dbGame.addGame(new Game("LBP2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("LBP3", GameType.Racing, 2008, 40));
		assertEquals(2, dbGame.findGameByType(GameType.Action).size());
	}

	@Test
	public void testDeleteGame() {
		dbGame.addGame(new Game("LBP2", GameType.Action, 2008, 40));
		dbGame.addGame(new Game("LBP3", GameType.Racing, 2008, 40));
		dbGame.deleteGame(dbGame.findGameByName("LBP3"));
		assertEquals(2, dbGame.getAllGames().size());	
	}

}
