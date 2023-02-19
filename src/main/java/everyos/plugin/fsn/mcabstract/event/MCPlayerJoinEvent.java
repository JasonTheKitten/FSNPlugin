package everyos.plugin.fsn.mcabstract.event;

import everyos.plugin.fsn.mcabstract.MCPlayer;

public interface MCPlayerJoinEvent extends MCEvent {

	MCPlayer getPlayer();
	
}
