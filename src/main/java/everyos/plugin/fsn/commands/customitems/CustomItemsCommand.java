package everyos.plugin.fsn.commands.customitems;

import java.util.List;

import everyos.plugin.fsn.argument.ArgumentReader;
import everyos.plugin.fsn.argument.InvalidArgumentException;
import everyos.plugin.fsn.argument.SubcommandArgumentReader;
import everyos.plugin.fsn.argument.SuggestionsArgumentReader;
import everyos.plugin.fsn.customitem.CustomItem;
import everyos.plugin.fsn.customitem.CustomItemStore;
import everyos.plugin.fsn.mcabstract.MCCommandSender;
import everyos.plugin.fsn.mcabstract.command.CommandArguments;
import everyos.plugin.fsn.mcabstract.command.GeneralCommand;
import everyos.plugin.fsn.mcabstract.command.MCCommand;
import everyos.plugin.fsn.mcabstract.command.PlayerCommand;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;

public class CustomItemsCommand implements GeneralCommand {

	private final CustomItemStore itemStore;

	public CustomItemsCommand(CustomItemStore itemStore) {
		this.itemStore = itemStore;
	}
	
	@Override
	public boolean execute(MCCommandSender sender, CommandArguments arguments) {
		if (arguments.length() < 2) {
			throw new InvalidArgumentException();
		}
		
		String itemName = arguments.read(0, getItemNameReader());
		MCCommand subcommand = arguments.read(1, getSubcommandReader(itemName));
		if (subcommand instanceof PlayerCommand playerCommand && sender instanceof MCPlayer player) {
			playerCommand.execute(player, arguments.offset(2));
		} else if (subcommand instanceof GeneralCommand generalCommand) {
			generalCommand.execute(sender, arguments.offset(2));
		} else {
			return false;
		}
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCCommandSender sender, CommandArguments arguments) {
		if (arguments.length() == 1) {
			return arguments.autocomplete(0, getItemNameReader());
		} else if (arguments.length() == 2) {
			String itemName = arguments.read(0, getItemNameReader());
			return arguments.autocomplete(1, getSubcommandReader(itemName));
		} else if (arguments.length() > 2) {
			String itemName = arguments.read(0, getItemNameReader());
			MCCommand subcommand = arguments.read(1, getSubcommandReader(itemName));
			if (subcommand instanceof PlayerCommand playerCommand && sender instanceof MCPlayer player) {
				return playerCommand.autocomplete(player, arguments.offset(2));
			} else if (subcommand instanceof GeneralCommand generalCommand) {
				return generalCommand.autocomplete(sender, arguments.offset(2));
			} else {
				return List.of();
			}
		} else {
			return List.of();
		}
	}

	private ArgumentReader<String> getItemNameReader() {
		return new SuggestionsArgumentReader(getCustomItemNames());
	}
	
	private ArgumentReader<MCCommand> getSubcommandReader(String itemName) {
		SubcommandArgumentReader subcommandReader = new SubcommandArgumentReader();
		if (itemStore.hasCustomItem(itemName)) {
			subcommandReader.addCommand("stats", new CustomItemsStatsCommand(itemStore, itemName));
			subcommandReader.addCommand("delete", new CustomItemsDeleteCommand(itemStore, itemName));
		} else {
			subcommandReader.addCommand("create", new CustomItemsCreateCommand(itemStore, itemName));
		}
		return subcommandReader;
	}

	private String[] getCustomItemNames() {
		CustomItem[] customItems = itemStore.getCustomItems();
		String[] itemNames = new String[customItems.length];
		for (int i = 0; i < customItems.length; i++) {
			itemNames[i] = customItems[i].getName();
		}
		
		return itemNames;
	}

}
