package everyos.plugin.fsn.commandrunner.bukkit;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.GeneralCommand;
import everyos.plugin.fsn.commandrunner.MCCommand;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.commandrunner.argument.InvalidArgumentException;
import everyos.plugin.fsn.mcabstract.MCCommandSender;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCCommandSender;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCPlayer;

public class BukkitTabCompleter implements TabCompleter {

	private final Plugin plugin;
	private final MCCommand command;
	
	public BukkitTabCompleter(Plugin plugin, MCCommand command) {
		this.plugin = plugin;
		this.command = command;
	}

	@Override
	public List<String> onTabComplete(CommandSender rawSender, Command rawCommand, String label, String[] args) {
		CommandArguments arguments = new BukkitCommandArguments(args, 0);
		MCCommandSender sender;
		if (rawSender instanceof Player) {
			sender = new BukkitMCPlayer(plugin, (Player) rawSender);
		} else {
			sender = new BukkitMCCommandSender(plugin, rawSender);
		}
		
		try {
			if (command instanceof PlayerCommand && sender instanceof MCPlayer) {
				return ((PlayerCommand) command).autocomplete((MCPlayer) sender, arguments);
			} else if (command instanceof GeneralCommand) {
				return ((GeneralCommand) command).autocomplete(sender, arguments);
			}
		} catch (InvalidArgumentException e) {
			return List.of();
		}
		
		return null;
	}

}
