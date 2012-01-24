package mieszko.events;

import mieszko.project.*;

public class GameEvent {

	private Game _game;

	public Game get_game() {
		return _game;
	}

	public void set_game(Game _game) {
		this._game = _game;
	}

	public GameEvent(Object source, Game game) {
		super();
		_game = game;
	}

}
