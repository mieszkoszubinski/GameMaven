package mieszko.events;

import java.util.*;

import mieszko.project.*;

import org.apache.log4j.Logger;


public class BackupGame implements IGameProcesses {

	private static Logger logger = Logger.getLogger(BackupGame.class);
    Calendar calendar = GregorianCalendar.getInstance();
	
	@Override
	public void processGame(GameEvent event) {
		event.get_game().setBackup(true);
		logger.info(event.get_game().getName() + " - backup done at " + calendar.getTime());
	}

}
