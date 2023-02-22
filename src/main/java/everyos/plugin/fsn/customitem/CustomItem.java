package everyos.plugin.fsn.customitem;

import everyos.plugin.fsn.mcabstract.stats.ItemStats;

public interface CustomItem {

	String getName();
	
	String getMaterialName();
	
	String getLore();
	
	CustomItemRarity getRarity();
	
	AbilitySet getAbilities();
	
	ItemStats getStats();
	
}
