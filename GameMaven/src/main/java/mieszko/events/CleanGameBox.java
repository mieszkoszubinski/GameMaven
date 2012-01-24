package mieszko.events;

import java.util.Calendar;
import java.util.GregorianCalendar;

import mieszko.project.*;

import org.apache.log4j.Logger;


public class CleanGameBox implements IGameProcesses {

	private static Logger logger = Logger.getLogger(CleanGameBox.class);
    Calendar calendar = GregorianCalendar.getInstance();

	@Override
	public void processGame(GameEvent event) {
		event.get_game().setCleanBox(true);
		logger.info(event.get_game().getName() + " - cleaned game box at " + calendar.getTime());

	}

}
