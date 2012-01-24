package mieszko.events;

import java.util.Calendar;
import java.util.GregorianCalendar;

import mieszko.project.*;

import org.apache.log4j.Logger;


public class ChangeGameBox implements IGameProcesses {

	private static Logger logger = Logger.getLogger(ChangeGameBox.class);
    Calendar calendar = GregorianCalendar.getInstance();
	
	@Override
	public void processGame(GameEvent event) {
		event.get_game().setGameBoxColor("standard black");
		logger.info(event.get_game().getName() + " - changed box for standard black at " + calendar.getTime());
	}
}
