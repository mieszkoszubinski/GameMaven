package mieszko.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mieszko.project.*;
import mieszko.services.*;

public class PersonGameDBManagerTest {
	
	PersonDBManager dbPerson = new PersonDBManager();
	GameDBManager dbGame = new GameDBManager();
	PersonGameDBManager dbPersonGame = new PersonGameDBManager();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbPerson.addPerson(new Person("Tomasz Ogon"));
		dbPerson.addPerson(new Person("Andrzej Mistrz"));
		dbGame.addGame(new Game("LBP", GameType.Action, 2006, 40));
		dbGame.addGame(new Game("Uncharted", GameType.Adventure, 2008, 80));
		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Tomasz Ogon"), dbGame.findGameByName("LBP"));
	}

	@After
	public void tearDown() throws Exception {
		dbPersonGame.deleteAllPersonGame();
		dbPerson.deleteAllPerson();
		dbGame.deleteAllGame();
	}
	
	@Test
	public void testAddGameToPerson() {
		dbGame.addGame(new Game("Uncharted2", GameType.Adventure, 2009, 40));
		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Tomasz Ogon"), dbGame.findGameByType(GameType.Adventure));
		assertEquals(3, dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")).size());
	}

	@Test
	public void testDeleteAllGameFromPerson() {
		dbGame.addGame(new Game("Uncharted2", GameType.Adventure, 2009, 40));
		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Andrzej Mistrz"), dbGame.findGameByType(GameType.Adventure));
		assertTrue(dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")).size() == 1);
		assertTrue(dbPersonGame.getPersonGame(dbPerson.findPersonByName("Andrzej Mistrz")).size() == 2);
		dbPersonGame.deleteAllGameFromPerson(dbPerson.findPersonByName("Andrzej Mistrz"));
		assertTrue(dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")).size() == 1);
		assertTrue(dbPersonGame.getPersonGame(dbPerson.findPersonByName("Andrzej Mistrz")).size() == 0);
	}

	@Test
	public void testDeleteAllPersonGame() {
		dbGame.addGame(new Game("Uncharted2", GameType.Adventure, 2009, 40));
		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Tomasz Ogon"), dbGame.findGameByType(GameType.Adventure));
		assertNotNull(dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")));
		dbPersonGame.deleteAllPersonGame();
		assertTrue(dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")).size() == 0);
	}

	@Test
	public void testGetPersonGame() {
		assertEquals(1, dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")).size());
	}

}
