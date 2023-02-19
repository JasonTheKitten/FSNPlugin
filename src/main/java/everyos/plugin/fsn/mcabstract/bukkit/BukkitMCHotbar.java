package everyos.plugin.fsn.mcabstract.bukkit;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.MCHotbar;
import everyos.plugin.fsn.mcabstract.MCItemStack;

public class BukkitMCHotbar implements MCHotbar {

	private final Plugin plugin;
	private final PlayerInventory inventory;

	public BukkitMCHotbar(Plugin plugin, PlayerInventory inventory) {
		this.plugin = plugin;
		this.inventory = inventory;
	}

	@Override
	public Optional<MCItemStack> getMainSelectedItemStack() {
		ItemStack itemStack = inventory.getItemInMainHand();
		if (itemStack.getAmount() == 0) {
			return Optional.empty();
		}
		
		MCItemStack items = new BukkitMCItemStack(plugin, itemStack);
		return Optional.of(items);
	}

}
