package everyos.plugin.fsn.mcabstract.imp.bukkit.command;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import everyos.plugin.fsn.mcabstract.command.CommandRegistry;
import everyos.plugin.fsn.mcabstract.command.MCCommand;

public class BukkitMCCommandRegistry implements CommandRegistry {

	private JavaPlugin plugin;

	public BukkitMCCommandRegistry(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void addCommand(String name, MCCommand command) {
		PluginCommand rawCommand = plugin.getCommand(name);
		rawCommand.setTabCompleter(new BukkitTabCompleter(plugin, command));
		rawCommand.setExecutor(new BukkitCommandExecutor(plugin, command));
	}
	
}
