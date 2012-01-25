package mieszko.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mieszko.project.Person;
import mieszko.services.PersonDBManager;

public class PersonDBManagerTest {
	
	PersonDBManager dbPerson = new PersonDBManager();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbPerson.addPerson(new Person("Tomasz Ogon"));
	}

	@After
	public void tearDown() throws Exception {
		dbPerson.deleteAllPerson();
	}

	@Test
	public void testAddPerson() {
		dbPerson.addPerson(new Person("Andrzej Mistrz"));
		assertEquals(2, dbPerson.getAllPersons().size());
	}

	@Test
	public void testGetAllPersons() {
		dbPerson.addPerson(new Person("Andrzej Mistrz"));
		dbPerson.addPerson(new Person("Dawid Deska"));
		dbPerson.addPerson(new Person("Daniel Budzik"));
		assertEquals(4, dbPerson.getAllPersons().size());
	}

	@Test
	public void testDeleteAllPerson() {
		dbPerson.addPerson(new Person("Andrzej Mistrz"));
		dbPerson.addPerson(new Person("Dawid Deska"));
		dbPerson.addPerson(new Person("Daniel Budzik"));
		dbPerson.deleteAllPerson();
		assertEquals(0, dbPerson.getAllPersons().size());
	}

	@Test
	public void testFindPersonByName() {
		dbPerson.addPerson(new Person("Andrzej Mistrz"));
		dbPerson.addPerson(new Person("Dawid Deska"));
		dbPerson.addPerson(new Person("Daniel Budzik"));
		assertEquals(1, dbPerson.findPersonByName("Daniel Budzik").size());
		assertTrue(dbPerson.findPersonByName("Daniel Budzik").size() == 1);
	}

	@Test
	public void testDeletePerson() {
		dbPerson.addPerson(new Person("Andrzej Mistrz"));
		dbPerson.addPerson(new Person("Dawid Deska"));
		dbPerson.addPerson(new Person("Daniel Budzik"));
		assertTrue(dbPerson.getAllPersons().size() == 4);
		dbPerson.deletePerson(dbPerson.findPersonByName("Andrzej Mistrz"));
		assertTrue(dbPerson.getAllPersons().size() == 3);
	}

}
