package mieszko.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import mieszko.project.*;


public class GameDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addGameStmt;
	private PreparedStatement getGameStmt;
	private PreparedStatement deleteAllGameStmt;
	private PreparedStatement findGameByNameStmt;
	private PreparedStatement findGameByTypeStmt;
	private PreparedStatement deleteGameStmt;
	
	List<Integer> listID = new ArrayList<Integer>();
	
	public GameDBManager() 
	{
		try 
		{
			Properties props = new Properties();
			
			try {
				props.load(ClassLoader.getSystemResourceAsStream("mieszko/jdbs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			conn = DriverManager
					.getConnection(props.getProperty("url"));

			stmt = conn.createStatement();
			boolean GameTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) 
			{
				if ("Game".equalsIgnoreCase(rs.getString("TABLE_NAME"))) 
				{
					GameTableExists = true;
					break;
				}
			}

			if (!GameTableExists) 
			{
				stmt.executeUpdate("CREATE TABLE Game(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,name varchar(40), gameType varchar(20), releaseYear integer, price integer)");
			}


			addGameStmt = conn.prepareStatement("INSERT INTO Game (name, gameType, releaseYear, price) VALUES (?, ?, ?, ?)");

			getGameStmt = conn.prepareStatement("SELECT * FROM Game");
			
			deleteAllGameStmt = conn.prepareStatement("DELETE FROM Game");
			
			findGameByNameStmt = conn.prepareStatement("SELECT id FROM game WHERE name = ?");
			
			findGameByTypeStmt = conn.prepareStatement("SELECT id FROM game WHERE gameType = ?");
			
			deleteGameStmt = conn.prepareStatement("DELETE FROM game WHERE id = ?");
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void addGame(Game Game) 
	{
		try 
		{
			addGameStmt.setString(1, Game.getName());
			addGameStmt.setString(2, Game.getGameType().toString());
			addGameStmt.setInt(3, Game.getReleaseYear());
			addGameStmt.setInt(4, Game.getPrice());
			addGameStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}

	public List<Game> getAllGames() 
	{
		List<Game> Games = new ArrayList<Game>();
		try 
		{
			ResultSet rs = getGameStmt.executeQuery();
			while (rs.next()) 
			{
				GameType gameType = null;
				if (rs.getString("gameType").equals("Action"))
					gameType = GameType.Action;
				if (rs.getString("gameType").equals("Adventure"))
					gameType = GameType.Adventure;
				if (rs.getString("gameType").equals("Shooter"))
					gameType = GameType.Shooter;
				if (rs.getString("gameType").equals("Fighting"))
					gameType = GameType.Fighting;
				if (rs.getString("gameType").equals("Strategy"))
					gameType = GameType.Strategy;
				if (rs.getString("gameType").equals("Sport"))
					gameType = GameType.Sport;
				if (rs.getString("gameType").equals("Racing"))
					gameType = GameType.Racing;
				
				Games.add(new Game(rs.getString("name"),gameType,rs.getInt("releaseYear"),rs.getInt("price")));
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Games;
	}

	public void deleteAllGame() 
	{
		try 
		{
			deleteAllGameStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Integer> findGameByName(String name)
	{
		try 
		{
			List<Integer> result = new ArrayList<Integer>();
			findGameByNameStmt.setString(1, name);
			ResultSet rs = findGameByNameStmt.executeQuery();
			while (rs.next())
				result.add(rs.getInt("ID"));	
			return result;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Integer> findGameByType(GameType gameType)
	{
		try 
		{
			List<Integer> result = new ArrayList<Integer>();
			findGameByTypeStmt.setString(1, gameType.toString());
			ResultSet rs = findGameByTypeStmt.executeQuery();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteGame(List<Integer> list)
	{
		try 
		{
			for (Integer id : list)
			{
				deleteGameStmt.setInt(1, id);
				deleteGameStmt.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	

	


}
