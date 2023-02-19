package everyos.plugin.fsn.handlers;

import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEventListener;

public class PlayerLevelHandler implements MCPlayerDeathEventListener {

	@Override
	public void onPlayerDeathEvent(MCPlayerDeathEvent event) {
		event.setXPKeepLevel(true);
	}

}
