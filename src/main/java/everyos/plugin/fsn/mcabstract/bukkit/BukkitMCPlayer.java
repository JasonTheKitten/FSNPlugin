package everyos.plugin.fsn.mcabstract.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.MCHotbar;
import everyos.plugin.fsn.mcabstract.MCInventory;
import everyos.plugin.fsn.mcabstract.MCPlayer;

public class BukkitMCPlayer extends BukkitMCCommandSender implements MCPlayer {

	private final Plugin plugin;
	private final Player player;

	public BukkitMCPlayer(Plugin plugin, Player player) {
		super(plugin, player);
		this.plugin = plugin;
		this.player = player;
	}
	
	@Override
	public MCInventory getInventory() {
		return new BukkitMCInventory(player.getInventory());
	}

	@Override
	public MCHotbar getHotbar() {
		return new BukkitMCHotbar(plugin, player.getInventory());
	}

}
