package mieszko.project;

import mieszko.events.*;
import mieszko.services.GameDBManager;
import mieszko.services.PersonDBManager;
import mieszko.services.PersonGameDBManager;

import org.apache.log4j.*;

import mieszko.events.BackupGame;
import mieszko.events.ChangeGameBox;
import mieszko.events.CleanGameBox;
import mieszko.events.IGameProcesses;
import mieszko.events.MasterOfGame;
import mieszko.project.Game;
import mieszko.project.GameType;
import mieszko.project.Person;
import mieszko.project.PriceException;




public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws PriceException {

		PropertyConfigurator.configure("src/resources/java/mieszko/Log4J.properties");

		Person person01 = new Person("Tomek Ogon");
		Person person02 = new Person("Andrzej Mistrz");
		try {
			person01.addGame("Skate 3", GameType.Sport, 2008, 80);
			person01.addGame("LBP", GameType.Action, 2006, 40);
			person01.addGame("God of War", GameType.Action, 2011, 99);
			person01.addGame("Uncharted 3", GameType.Adventure, 2011, 189);
			person01.addGame("LBP 2", GameType.Action, 2011, -2);
			person01.addGame("Uncharted 2", GameType.Adventure, 2008, 79);
		} catch (PriceException exception) {
			logger.error(exception);
		}

		person01.printAll();

		person01.editGamePrize(person01.findAllGameByType(GameType.Action), 1);
		person01.editGameReleaseYear(person01.findAllGameByName("Skate 3"),
				2011);
		person01.editGamePrize(person01.findAllGameByReleaseYear(2006), 66);
		try {
			person01.editGamePrize(person01.findAllGameByName("LBP"), -1);
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
		person01.findAllGameByName("LBP").get(0).setCleanBox(false);
		System.out.println("LBP Clen box before - "
				+ person01.findAllGameByName("LBP").get(0).isCleanBox());
		System.out.println("LBP Color box before - "
				+ person01.findAllGameByName("LBP").get(0).getGameBoxColor());
		System.out.println("LBP Backup game before - "
				+ person01.findAllGameByName("LBP").get(0).isBackup());
		System.out.println("GoW Clen box before - "
				+ person01.findAllGameByName("God of War").get(0).isCleanBox());
		System.out.println("GoW Color box before - "
				+ person01.findAllGameByName("God of War").get(0).getGameBoxColor());
		System.out.println("GoW Backup game before - "
				+ person01.findAllGameByName("God of War").get(0).isBackup());
		masterOfGame.addProcess(cleanGameBox);
		masterOfGame.addProcess(changeGameBox);
		masterOfGame.addProcess(backupGame);
		masterOfGame.executeProcesses(person01.findAllGameByType(GameType.Action));
		System.out.println("LBP Clen box after - "
				+ person01.findAllGameByName("LBP").get(0).isCleanBox());
		System.out.println("LBP Color box after - "
				+ person01.findAllGameByName("LBP").get(0).getGameBoxColor());
		System.out.println("LBP Backup game after - "
				+ person01.findAllGameByName("LBP").get(0).isBackup());
		System.out.println("GoW Clen box after - "
				+ person01.findAllGameByName("God of War").get(0).isCleanBox());
		System.out.println("GoW Color box after - "
				+ person01.findAllGameByName("God of War").get(0).getGameBoxColor());
		System.out.println("GoW Backup game after - "
				+ person01.findAllGameByName("God of War").get(0).isBackup());
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
		dbGame.deleteGame(dbGame.findGameByName("LBP"));
		System.out.println("---Po usuwaniu");
		for (Game game : dbGame.getAllGames())
		{
			System.out.println("Name: " + game.getName() + "\tGameType: " + game.getGameType() + "\tReleasedYear: " + game.getReleaseYear() + "\tPrice: " + game.getPrice());
		}
*/
		PersonGameDBManager dbPersonGame = new PersonGameDBManager();

		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Tomasz Ogon"), dbGame.findGameByName("LBP"));
		dbPersonGame.addGameToPerson(dbPerson.findPersonByName("Andrzej Mistrz"), dbGame.findGameByType(GameType.Action));

		System.out.println("**********----------**********");
		System.out.println("Lista gier Tomasz Ogon");
		
		for (Game game : dbPersonGame.getPersonGame(dbPerson.findPersonByName("Tomasz Ogon")))
		{
			System.out.println("Name: " + game.getName() + "\tGameType: " + game.getGameType() + "\tReleasedYear: " + game.getReleaseYear() + "\tPrice: " + game.getPrice());
		}
	}

}