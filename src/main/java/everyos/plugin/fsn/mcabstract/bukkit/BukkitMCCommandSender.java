package everyos.plugin.fsn.mcabstract.bukkit;

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
	public void sendLocalizedMessage(String label, String... fillins) {
		// TODO: Unwanted cast
		LocalizationProvider localizationProvider = ((FSNPlugin) plugin)
			.getCurrentInstance()
			.getDefaultLocalizationProvider();
		String message = localizationProvider.localize(label, fillins);
		sender.sendMessage(message);
	}

	@Override
	public void sendRawMessage(String message) {
		sender.sendMessage(message);
	}

}