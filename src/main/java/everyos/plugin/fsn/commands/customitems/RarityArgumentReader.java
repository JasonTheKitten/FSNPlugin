package everyos.plugin.fsn.commands.customitems;

import java.util.ArrayList;
import java.util.List;

import everyos.plugin.fsn.argument.ArgumentReader;
import everyos.plugin.fsn.argument.InvalidArgumentException;
import everyos.plugin.fsn.customitem.CustomItemRarity;

public class RarityArgumentReader implements ArgumentReader<CustomItemRarity> {
	
	@Override
	public CustomItemRarity read(String value) {
		for (CustomItemRarity rarity: CustomItemRarity.values()) {
			if (rarity.toString().equalsIgnoreCase(value)) {
				return rarity;
			}
		}
		
		throw new InvalidArgumentException();
	}

	@Override
	public List<String> autocomplete(String value) {
		List<String> autocompletes = new ArrayList<>();
		for (CustomItemRarity rarity: CustomItemRarity.values()) {
			String option = rarity.toString();
			if (option.toLowerCase().contains(value.toLowerCase())) {
				autocompletes.add(option);
			}
		}
		
		return autocompletes;
	}

}
