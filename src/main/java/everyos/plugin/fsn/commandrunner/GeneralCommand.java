package everyos.plugin.fsn.commandrunner;

import java.util.List;

import everyos.plugin.fsn.mcabstract.MCCommandSender;

public interface GeneralCommand extends MCCommand {

	boolean execute(MCCommandSender sender, CommandArguments arguments);
	
	List<String> autocomplete(MCCommandSender sender, CommandArguments arguments);
	
}
