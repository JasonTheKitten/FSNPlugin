package everyos.plugin.fsn.mcabstract.event;

import everyos.plugin.fsn.mcabstract.MCPlayer;

public interface MCPlayerDeathEvent extends MCEvent {

	MCPlayer getPlayer();
	
	void setXPKeepLevel(boolean keepLevel);

}
