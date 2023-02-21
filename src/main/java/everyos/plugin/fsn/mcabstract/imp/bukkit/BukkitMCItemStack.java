package everyos.plugin.fsn.mcabstract.imp.bukkit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.MCItemStack;
import everyos.plugin.fsn.mcabstract.imp.bukkit.stats.BukkitItemStats;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;

public class BukkitMCItemStack implements MCItemStack {

	private final Plugin plugin;
	private final ItemStack itemStack;

	public BukkitMCItemStack(Plugin plugin, ItemStack itemStack) {
		this.plugin = plugin;
		this.itemStack = itemStack;
	}

	@Override
	public ItemStats getItemStats() {
		return new BukkitItemStats(plugin, itemStack);
	}

}
