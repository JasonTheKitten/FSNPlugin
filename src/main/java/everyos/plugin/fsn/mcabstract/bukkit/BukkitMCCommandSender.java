package everyos.plugin.fsn.mcabstract.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.FSNPlugin;
import everyos.plugin.fsn.localization.LocalizationProvider;
import everyos.plugin.fsn.mcabstract.MCCommandSender;

public class BukkitMCCommandSender implements MCCommandSender {
	
	private final Plugin plugin;
	private final CommandSender sender;

	public BukkitMCCommandSender(Plugin plugin, CommandSender sender) {
		this.plugin = plugin;
		this.sender = sender;
	}
	
	@Override
	public LocalizationProvider getLocalizationProvider() {
		// TODO: Unwanted cast
		return ((FSNPlugin) plugin)
			.getCurrentInstance()
			.getDefaultLocalizationProvider();
	}

	@Override
	public void sendLocalizedMessage(String label, String... fillins) {
		String message = getLocalizationProvider().localize(label, fillins);
		String coloredMsg = ChatColor.translateAlternateColorCodes('&', message);
		
		sender.sendMessage(coloredMsg);
	}

	@Override
	public void sendRawMessage(String message) {
		String coloredMsg = ChatColor.translateAlternateColorCodes('&', message);
		sender.sendMessage(coloredMsg);
	}

}
