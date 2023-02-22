package everyos.plugin.fsn.commands.customitems;

import java.util.List;
import java.util.Optional;

import everyos.plugin.fsn.argument.ArgumentReader;
import everyos.plugin.fsn.argument.InvalidArgumentException;
import everyos.plugin.fsn.customitem.CustomItemCreationOptions;
import everyos.plugin.fsn.customitem.CustomItemRarity;
import everyos.plugin.fsn.customitem.CustomItemStore;
import everyos.plugin.fsn.mcabstract.command.CommandArguments;
import everyos.plugin.fsn.mcabstract.command.PlayerCommand;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;

public class CustomItemsCreateCommand implements PlayerCommand {
	
	private final ArgumentReader<CustomItemRarity> rarityArgumentReader = new RarityArgumentReader();
	
	private final CustomItemStore itemStore;
	private final String itemName;

	public CustomItemsCreateCommand(CustomItemStore itemStore, String itemName) {
		this.itemStore = itemStore;
		this.itemName = itemName;
	}

	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() != 1) {
			throw new InvalidArgumentException();
		}
		
		CustomItemRarity rarity = arguments.read(0, rarityArgumentReader);
		Optional<String> material = getHeldMaterial(player);
		String lore = "Lore not implemented";
		
		if (!material.isPresent()) {
			player.sendLocalizedMessage("item.notheld");
			return false;
		}
		
		CustomItemCreationOptions creationOptions = new CustomItemCreationOptions(itemName, material.get(), rarity, lore);
		itemStore.createCustomItem(creationOptions);
		
		player.sendLocalizedMessage("item.customcreated");
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() == 1) {
			return arguments.autocomplete(0, rarityArgumentReader);
		}
		
		return List.of();
	}
	
	private Optional<String> getHeldMaterial(MCPlayer player) {
		return player
			.getHotbar()
			.getMainSelectedItemStack()
			.map(itemStack -> itemStack.getMaterial().getName());
	}

}
