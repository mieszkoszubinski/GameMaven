package mieszko.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mieszko.project.Game;
import mieszko.project.GameType;
import mieszko.project.Person;
import mieszko.project.PriceException;

public class PersonTest {

	Person person01 = new Person("Tomasz Ogon");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		person01.addGame("LBP", GameType.Action, 2006, 40);
	}

	@After
	public void tearDown() throws Exception {
		person01.deleteAllGame();
	}

	@Test
	public void testPerson() throws PriceException {
		Person person02 = new Person("Andrzej Mistrz");
		person02.addGame("LBP 2", GameType.Adventure, 2010, 99);
		assertTrue(person02.getListOfGame().size() == 1);
		assertTrue(person02.getName().equals("Andrzej Mistrz"));
	}

	@Test
	public void testAddGame() throws PriceException {
		assertTrue(person01.getListOfGame().size() == 1);
	}

	@Test
	public void testDeleteGame() throws PriceException {
		person01.deleteGame(person01.findAllGameByName("LBP"));
		assertTrue(person01.getListOfGame().size() == 0);
	}

	@Test
	public void testDeleteAllGame() throws PriceException {
		person01.addGame("LBP 2", GameType.Action, 2010, 500);
		person01.addGame("LBP 3", GameType.Action, 2011, 800);
		person01.deleteAllGame();
		assertTrue(person01.getListOfGame().size() == 0);
	}

	@Test
	public void testEditGamePrize() throws PriceException {
		person01.addGame("Uncharted", GameType.Adventure, 2006, 99);
		person01.editGamePrize(person01.findAllGameByReleaseYear(2006),29);
		assertTrue(person01.getListOfGame().get(0).getPrice() == 29);
		assertTrue(person01.getListOfGame().get(1).getPrice() == 29);
		assertSame(29, person01.getListOfGame().get(0).getPrice());
		assertSame(29, person01.getListOfGame().get(1).getPrice());
	}

	@Test
	public void testEditGameReleaseYear() throws PriceException {
		person01.editGameReleaseYear(person01.findAllGameByName("LBP"), 2010);
		assertTrue(person01.getListOfGame().get(0).getReleaseYear() == 2010);
	}

	@Test
	public void testEditGameType() throws PriceException {
		person01.editGameType(person01.findAllGameByName("LBP"), GameType.Sport);
		assertTrue(person01.getListOfGame().get(0).getGameType() == GameType.Sport);
	}

	@Test
	public void testFindAllGameByName() throws PriceException {
		person01.addGame("LBP 2", GameType.Action, 2010, 500);
		assertEquals(person01.getListOfGame().get(0), person01.findAllGameByName("LBP").get(0));
		assertSame(person01.getListOfGame().get(1), person01.findAllGameByName("LBP 2").get(0));
		assertNotNull(person01.getListOfGame().get(1));
	}

	@Test
	public void testFindAllGameByType() throws PriceException {
		person01.addGame("LBP 2", GameType.Action, 2010, 500);
		assertEquals(person01.getListOfGame().get(0), person01.findAllGameByType(GameType.Action).get(0));
		assertSame(person01.getListOfGame().get(1), person01.findAllGameByType(GameType.Action).get(1));
	}

	@Test
	public void testFindAllGameByReleaseYear() throws PriceException {
		person01.addGame("LBP 2", GameType.Action, 2006, 500);
		person01.addGame("LBP 3", GameType.Action, 2006, 600);
		assertEquals(person01.getListOfGame().get(0), person01.findAllGameByReleaseYear(2006).get(0));
		assertSame(person01.getListOfGame().get(2), person01.findAllGameByReleaseYear(2006).get(2));
	}

	@Test
	public void testGetName() {
		assertTrue(person01.getName() == "Tomasz Ogon");
	}

	@Test
	public void testSetName() {
		person01.setName("Andrzej Mistrz");
		assertTrue(person01.getName() == "Andrzej Mistrz");
	}

	@Test
	public void testGetListOfGame() throws PriceException {
		assertNotNull(person01.getListOfGame());
		
	}
	
	@Test
	public void testSetListOfGame() {
		List<Game> list = new ArrayList<Game>();
		list.add(new Game("LBP", GameType.Action, 2006, 40));
		person01.setListOfGame(list);
		assertTrue(list.size()==1);
	}

	@Test(expected = PriceException.class, timeout = 100)
	public void testPriceException() throws PriceException {
		person01.addGame("LBP", GameType.Action, 2006, -5);
	}

}
