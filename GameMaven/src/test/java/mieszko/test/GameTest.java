package mieszko.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mieszko.project.GameType;
import mieszko.project.Person;
import mieszko.project.PriceException;

public class GameTest {
	
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
	}

	@Test
	public void testGame() throws PriceException {
		assertTrue(person01.getListOfGame().size()>0);
	}
/*
	@Test
	public void testPrintGame() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetName() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).getName().equals("LBP"));
	}

	@Test
	public void testSetName() throws PriceException {
		person01.getListOfGame().get(0).setName("LBP 2");
		assertTrue(person01.getListOfGame().get(0).getName().equals("LBP 2"));
	}

	@Test
	public void testGetGameType() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).getGameType().equals(GameType.Action));
	}

	@Test
	public void testSetGameType() throws PriceException {
		person01.getListOfGame().get(0).setGameType(GameType.Racing);
		assertTrue(person01.getListOfGame().get(0).getGameType().equals(GameType.Racing));
	}

	@Test
	public void testGetReleaseYear() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).getReleaseYear()==2006);
	}

	@Test
	public void testSetReleaseYear() throws PriceException {
		person01.getListOfGame().get(0).setReleaseYear(2010);
		assertTrue(person01.getListOfGame().get(0).getReleaseYear()==2010);
	}

	@Test
	public void testGetPrice() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).getPrice()==40);
	}

	@Test
	public void testSetPrice() throws PriceException {
		person01.getListOfGame().get(0).setPrice(9);
		assertTrue(person01.getListOfGame().get(0).getPrice()==9);
	}

	@Test
	public void testIsCleanBox() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).isCleanBox()==true);
	}

	@Test
	public void testSetCleanBox() throws PriceException {
		person01.getListOfGame().get(0).setCleanBox(false);
		assertTrue(person01.getListOfGame().get(0).isCleanBox()==false);
	}

	@Test
	public void testIsBackup() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).isBackup()==false);
	}

	@Test
	public void testSetBackup() throws PriceException {
		person01.getListOfGame().get(0).setCleanBox(true);
		assertTrue(person01.getListOfGame().get(0).isCleanBox()==true);
	}
	
	@Test
	public void testGetGameBoxColor() throws PriceException {
		assertTrue(person01.getListOfGame().get(0).getGameBoxColor().equals("white"));
	}
	
	@Test
	public void testSetGameBoxColor() throws PriceException {
		person01.getListOfGame().get(0).setGameBoxColor("pink");
		assertTrue(person01.getListOfGame().get(0).getGameBoxColor().equals("pink"));
	}

}
