package mieszko.project;

import java.util.*;
import org.apache.log4j.*;

public class Person {

	private static Logger logger = Logger.getLogger(Person.class);

	String name;
	List<Game> listOfGame = new ArrayList<Game>();

	public Person(String name) {
		super();
		this.name = name;
		this.listOfGame = new ArrayList<Game>();
	}

	public void printGame() {
		for (Game game : listOfGame) {
			game.printGame();
		}
	}

	public void printPerson() {
		System.out.println("##### " + name + " #####");
	}

	public void printAll() {
		printPerson();
		System.out.println();
		printGame();
		System.out.println();
	}

	public void addGame(String name, GameType gameType, int releaseYear,
			int price) throws PriceException {
		if (price > 0) {
			listOfGame.add(new Game(name, gameType, releaseYear, price));
			logger.info("Game: " + name + " - added");
		}
		if (price <= 0)
			throw new PriceException("Price cannot by less than 0");
	}

	public void deleteGame(List<Game> list) {
		for (Game game : list) {
		listOfGame.remove(game);
		logger.info("Game: " + game + " - removed");
		}
	}

	public void deleteAllGame() {
		listOfGame.clear();
		logger.info("Removed all game");
	}

	public void editGamePrize(List<Game> list, int price) throws PriceException {
		if (price > 0) {
			for (Game game : list) {
				game.setPrice(price);
				logger.info("Prize edit in " + game.getName() + ", new price: "
						+ price);
			}
		}
		if (price <= 0)
			throw new PriceException("Price cannot by less than 0");
	}

	public void editGameReleaseYear(List<Game> list, int releaseYear) {
		for (Game game : list) {
			game.setReleaseYear(releaseYear);
			logger.info("Release year edit in " + game.getName()
					+ ", new release year: " + releaseYear);
		}
	}

	public void editGameType(List<Game> list, GameType gameType) {
		for (Game game : list) {
			game.setGameType(gameType);
			logger.info("Game type edit in " + game.getName()
					+ ", new game type: " + gameType);
		}
	}

	public List<Game> findAllGameByName(String name) {
		List<Game> results = new ArrayList<Game>();
		for (Game game : listOfGame) {
			if (game.getName().equals(name)) {
				results.add(game);
			}
		}
		return results;
	}

	public List<Game> findAllGameByType(GameType gameType) {
		List<Game> results = new ArrayList<Game>();
		for (Game game : listOfGame) {
			if (game.getGameType().equals(gameType)) {
				results.add(game);
			}
		}
		return results;
	}
	
	public List<Game> findAllGameByReleaseYear(int releaseYear) {
		List<Game> results = new ArrayList<Game>();
		for (Game game : listOfGame) {
			if (game.getReleaseYear()==releaseYear) {
				results.add(game);
			}
		}
		return results;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Person.logger = logger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getListOfGame() {
		return listOfGame;
	}

	public void setListOfGame(List<Game> listOfGame) {
		this.listOfGame = listOfGame;
	}
}
