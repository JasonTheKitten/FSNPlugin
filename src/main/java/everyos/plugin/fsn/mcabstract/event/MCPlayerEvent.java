package everyos.plugin.fsn.mcabstract.event;

import everyos.plugin.fsn.mcabstract.MCPlayer;

public interface MCPlayerEvent extends MCEvent {

	MCPlayer getPlayer();
	
}
