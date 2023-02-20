package everyos.plugin.fsn.mcabstract.imp.bukkit.entity;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.localization.LocalizationProvider;
import everyos.plugin.fsn.mcabstract.MCHotbar;
import everyos.plugin.fsn.mcabstract.MCInventory;
import everyos.plugin.fsn.mcabstract.MCXPBar;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCCommandSender;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCHotbar;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCInventory;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCXPBar;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class BukkitMCPlayer extends BukkitMCEntity implements MCPlayer {

	private final Plugin plugin;
	private final Player player;
	private final BukkitMCCommandSender commandSender;

	public BukkitMCPlayer(Plugin plugin, Player player) {
		super(plugin, player);
		this.plugin = plugin;
		this.player = player;
		this.commandSender = new BukkitMCCommandSender(plugin, player);
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
	public void setDisplayBarText(String displayBar) {
		String coloredMessage = ChatColor.translateAlternateColorCodes('&', displayBar);
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(coloredMessage));
	}

	@Override
	public MCXPBar getXPBar() {
		return new BukkitMCXPBar(player);
	}

	@Override
	public LocalizationProvider getLocalizationProvider() {
		return commandSender.getLocalizationProvider();
	}

	@Override
	public void sendLocalizedMessage(String string, String... fillins) {
		commandSender.sendLocalizedMessage(string, fillins);
	}

	@Override
	public void sendRawMessage(String string) {
		commandSender.sendRawMessage(string);
	}

}
