package everyos.plugin.fsn.commandrunner;

import java.util.List;

import everyos.plugin.fsn.mcabstract.entity.MCPlayer;

public interface PlayerCommand extends MCCommand {

	boolean execute(MCPlayer player, CommandArguments arguments);
	
	List<String> autocomplete(MCPlayer player, CommandArguments arguments);
	
}
