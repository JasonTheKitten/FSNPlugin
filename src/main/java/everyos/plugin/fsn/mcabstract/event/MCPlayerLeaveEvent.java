package everyos.plugin.fsn.mcabstract.event;

import everyos.plugin.fsn.mcabstract.MCPlayer;

public interface MCPlayerLeaveEvent extends MCEvent {

	MCPlayer getPlayer();
	
}
