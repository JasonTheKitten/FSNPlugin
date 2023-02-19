package everyos.plugin.fsn.mcabstract;

import java.util.UUID;

import everyos.plugin.fsn.mcabstract.stats.PlayerStats;

public interface MCPlayer extends MCCommandSender {

	MCInventory getInventory();
	
	MCHotbar getHotbar();

	PlayerStats getStats();
	
	MCXPBar getXPBar();

	void setDisplayBarText(String displayBar);

	UUID getUUID();
	
}
