package mieszko.project;

import mieszko.events.*;
import mieszko.services.GameDBManager;
import mieszko.services.PersonDBManager;
import mieszko.services.PersonGameDBManager;

import org.apache.log4j.*;



public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws PriceException {

		PropertyConfigurator.configure("src/resources/java/mieszko/Log4J.properties");

		Person person01 = new Person("Tomek Ogon");
		Person person02 = new Person("Andrzej Mistrz");
		try {
			person01.addGame("Call of Duty 3", GameType.Shooter, 2011, 99);
			person01.addGame("Battlefield 3", GameType.Shooter, 2011, 120);
			person01.addGame("Starcraft 2", GameType.Strategy, 2011, 99);
			person01.addGame("FIFA 11", GameType.Sport, 2010, 39);
			person01.addGame("Shogun 2", GameType.Strategy, 2011, -2);
			person01.addGame("Need For Speed Most Wanted", GameType.Racing, 2005, 79);
		} catch (PriceException exception) {
			logger.error(exception);
		}

		person01.printAll();

		person01.editGamePrize(person01.findAllGameByType(GameType.Action), 1);
		person01.editGameReleaseYear(person01.findAllGameByName("Call of Duty 3"),
				2011);
		person01.editGamePrize(person01.findAllGameByReleaseYear(2006), 66);
		try {
			person01.editGamePrize(person01.findAllGameByName("Battlefield 3"), -1);
		} catch (PriceException exception) {
			logger.error(exception);
		}

		person01.printAll();
//----------
		MasterOfGame masterOfGame = new MasterOfGame();

		IGameProcesses cleanGameBox = new CleanGameBox();
		IGameProcesses changeGameBox = new ChangeGameBox();
		IGameProcesses backupGame = new BackupGame();

		System.out.println("## Game processes on action game ##");
		person01.findAllGameByName("Battlefield 3").get(0).setCleanBox(false);
		System.out.println("Battlefield 3 Clen box before - "
				+ person01.findAllGameByName("Battlefield 3").get(0).isCleanBox());
		System.out.println("Battlefield 3 Color box before - "
				+ person01.findAllGameByName("Battlefield 3").get(0).getGameBoxColor());
		System.out.println("Battlefield 3 Backup game before - "
				+ person01.findAllGameByName("Battlefield 3").get(0).isBackup());
		System.out.println("Starcraft 2 Clen box before - "
				+ person01.findAllGameByName("Starcraft 2").get(0).isCleanBox());
		System.out.println("Starcraft 2 box before - "
				+ person01.findAllGameByName("Starcraft 2").get(0).getGameBoxColor());
		System.out.println("Starcraft 2 game before - "
				+ person01.findAllGameByName("Starcraft 2").get(0).isBackup());
		masterOfGame.addProcess(cleanGameBox);
		masterOfGame.addProcess(changeGameBox);
		masterOfGame.addProcess(backupGame);
		masterOfGame.executeProcesses(person01.findAllGameByType(GameType.Shooter));
		System.out.println("Battlefield 3 Clen box after - "
				+ person01.findAllGameByName("Battlefield 3").get(0).isCleanBox());
		System.out.println("Battlefield 3 Color box after - "
				+ person01.findAllGameByName("Battlefield 3").get(0).getGameBoxColor());
		System.out.println("Battlefield 3 Backup game after - "
				+ person01.findAllGameByName("Battlefield 3").get(0).isBackup());
		System.out.println("Call of Duty 3 Clen box after - "
				+ person01.findAllGameByName("Call of Duty 3").get(0).isCleanBox());
		System.out.println("Call of Duty 3 Color box after - "
				+ person01.findAllGameByName("Call of Duty 3").get(0).getGameBoxColor());
		System.out.println("Call of Duty 3 Backup game after - "
				+ person01.findAllGameByName("Call of Duty 3").get(0).isBackup());
//----------
		
		System.out.println("\n**********----------**********");
		
		PersonDBManager dbPerson = new PersonDBManager();

		dbPerson.addPerson(person01);
		dbPerson.addPerson(person02);
		for (Person person : dbPerson.getAllPersons())
		{
			System.out.println(person.getName());
		}
/*
		dbPerson.deletePerson(dbPerson.findGameByName("Andrzej Mistrz"));
		System.out.println("---Po usuwaniu");
		for (Person person : dbPerson.getAllPersons())
		{
			System.out.println(person.getName());
		}
*/
		
		
		System.out.println("**********----------**********");
		
		GameDBManager dbGame = new GameDBManager();

		dbGame.addGame(person01.getListOfGame().get(0));
		dbGame.addGame(person01.getListOfGame().get(1));
		dbGame.addGame(person01.getListOfGame().get(2));
		for (Game game : dbGame.getAllGames())
		{
			System.out.println("Name: " + game.getName() + "\tGameType: " + game.getGameType() + "\tReleasedYear: " + game.getReleaseYear() + "\tPrice: " + game.getPrice());
		}
		
/*
		dbGame.deleteGame(dbGame.findGameByName("Battlefield 3"));
		System.out.println("---Po usuwaniu");
		for (Game game : dbGame.getAllGames())
		{
			System.out.println("Name: " + game.getName() + "\tGameType: " + game.getGameType() + "\tReleasedYear: " + game.getReleaseYear() + "\tPrice: " + game.getPrice());
		}
*/
		PersonGameDBManager dbPersonGame = new PersonGameDBManager();

		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Tomasz Ogon"), dbGame.findGameByName("Battlefield 3"));
		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Andrzej Mistrz"), dbGame.findGameByType(GameType.Action));

		System.out.println("**********----------**********");
		System.out.println("Lista gier Tomasz Ogon");
		
		for (Game game : dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")))
		{
			System.out.println("Name: " + game.getName() + "\tGameType: " + game.getGameType() + "\tReleasedYear: " + game.getReleaseYear() + "\tPrice: " + game.getPrice());
		}
		
		System.out.println("**********----------**********");
		System.out.println("Lista gier Andrzej Mistrz");
		
		for (Game game : dbPersonGame.getPersonGame(dbPerson.findPersonByName("Andrzej Mistrz")))
		{
			System.out.println("Name: " + game.getName() + "\tGameType: " + game.getGameType() + "\tReleasedYear: " + game.getReleaseYear() + "\tPrice: " + game.getPrice());
		}
	}
}
		
		

		