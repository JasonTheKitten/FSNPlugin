package everyos.plugin.fsn.mcabstract.bukkit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.MCItemStack;
import everyos.plugin.fsn.stats.ItemStats;

public class BukkitMCItemStack implements MCItemStack {

	private final Plugin plugin;
	
	private ItemStack itemStack;

	public BukkitMCItemStack(Plugin plugin, ItemStack itemStack) {
		this.plugin = plugin;
		this.itemStack = itemStack;
	}

	@Override
	public ItemStats getItemStats() {
		PersistentDataContainer itemData = getItemStackData(itemStack);
		
		return new BukkitItemStats(plugin, itemData);
	}

	private PersistentDataContainer getItemStackData(ItemStack itemStack2) {
		return itemStack
			.getItemMeta()
			.getPersistentDataContainer();
	}

}
