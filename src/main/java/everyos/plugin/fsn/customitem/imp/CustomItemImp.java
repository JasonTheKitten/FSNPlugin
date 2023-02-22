package everyos.plugin.fsn.customitem.imp;

import everyos.plugin.fsn.customitem.AbilitySet;
import everyos.plugin.fsn.customitem.CustomItem;
import everyos.plugin.fsn.customitem.CustomItemCreationOptions;
import everyos.plugin.fsn.customitem.CustomItemRarity;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;

public class CustomItemImp implements CustomItem {

	private final CustomItemCreationOptions options;

	public CustomItemImp(CustomItemCreationOptions options) {
		this.options = options;
	}

	@Override
	public String getName() {
		return options.name();
	}

	@Override
	public String getMaterialName() {
		return options.materialName();
	}

	@Override
	public String getLore() {
		return options.lore();
	}

	@Override
	public CustomItemRarity getRarity() {
		return options.rarity();
	}

	@Override
	public AbilitySet getAbilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}

}
