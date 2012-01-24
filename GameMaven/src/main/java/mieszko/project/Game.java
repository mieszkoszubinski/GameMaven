package mieszko.project;

public class Game {

	public String name;
	public GameType gameType;
	public int releaseYear;
	public int price;
	public boolean cleanBox;
	public boolean backup;
	public String gameBoxColor;

	public Game(String name, GameType gameType, int releaseYear, int price) {
		this.name = name;
		this.gameType = gameType;
		this.releaseYear = releaseYear;
		this.price = price;
		this.cleanBox = true;
		this.backup = false;
		this.gameBoxColor = "white";

	}

	public void printGame() {
		System.out.println("Name: " + name + "\tGame type: " + gameType
				+ "\tRelease year: " + releaseYear + "\tPrice: " + price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isCleanBox() {
		return cleanBox;
	}

	public void setCleanBox(boolean cleanBox) {
		this.cleanBox = cleanBox;
	}

	public boolean isBackup() {
		return backup;
	}

	public void setBackup(boolean backup) {
		this.backup = backup;
	}

	public String getGameBoxColor() {
		return gameBoxColor;
	}

	public void setGameBoxColor(String gameBoxColor) {
		this.gameBoxColor = gameBoxColor;
	}

}
