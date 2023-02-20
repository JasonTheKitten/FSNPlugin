package everyos.plugin.fsn.mcabstract.entity;

import everyos.plugin.fsn.mcabstract.MCCommandSender;
import everyos.plugin.fsn.mcabstract.MCHotbar;
import everyos.plugin.fsn.mcabstract.MCInventory;
import everyos.plugin.fsn.mcabstract.MCXPBar;

public interface MCPlayer extends MCCommandSender, MCEntity {

	MCInventory getInventory();
	
	MCHotbar getHotbar();
	
	MCXPBar getXPBar();

	void setDisplayBarText(String displayBar);
	
}
