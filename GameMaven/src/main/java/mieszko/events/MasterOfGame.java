package mieszko.events;

import java.util.*;

import mieszko.project.*;


public class MasterOfGame {

	private Game game;

	private List processes = new ArrayList();

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public synchronized void addProcess(IGameProcesses process) {
		processes.add(process);
	}

	public synchronized void removeProcess(IGameProcesses process) {
		processes.remove(process);
	}

	public synchronized void executeProcesses(List<Game> list) {
		for (Game game : list) {
			setGame(game);
			GameEvent event = new GameEvent(this, game);
			Iterator proc = processes.iterator();
			while (proc.hasNext()) {
				((IGameProcesses) proc.next()).processGame(event);
			}
		}
	}

}
