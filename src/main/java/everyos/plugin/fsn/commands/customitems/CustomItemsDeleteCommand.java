package everyos.plugin.fsn.commands.customitems;

import java.util.List;

import everyos.plugin.fsn.customitem.CustomItemStore;
import everyos.plugin.fsn.mcabstract.MCCommandSender;
import everyos.plugin.fsn.mcabstract.command.CommandArguments;
import everyos.plugin.fsn.mcabstract.command.GeneralCommand;

public class CustomItemsDeleteCommand implements GeneralCommand {

	public CustomItemsDeleteCommand(CustomItemStore itemStore, String itemName) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(MCCommandSender sender, CommandArguments arguments) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> autocomplete(MCCommandSender sender, CommandArguments arguments) {
		return List.of();
	}

}
