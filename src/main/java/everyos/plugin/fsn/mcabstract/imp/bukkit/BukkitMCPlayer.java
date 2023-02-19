package everyos.plugin.fsn.mcabstract.imp.bukkit;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.MCHotbar;
import everyos.plugin.fsn.mcabstract.MCInventory;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.stats.PlayerStats;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

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
	
	@Override
	public PlayerStats getStats() {
		return BukkitPlayerStats.getStats(plugin, player);
	}
	
	@Override
	public void setDisplayBarText(String displayBar) {
		String coloredMessage = ChatColor.translateAlternateColorCodes('&', displayBar);
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(coloredMessage));
	}

	@Override
	public UUID getUUID() {
		return player.getUniqueId();
	}

}
