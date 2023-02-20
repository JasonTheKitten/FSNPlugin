package everyos.plugin.fsn.commandrunner.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.GeneralCommand;
import everyos.plugin.fsn.commandrunner.MCCommand;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.commandrunner.argument.InvalidArgumentException;
import everyos.plugin.fsn.localization.LocalizedException;
import everyos.plugin.fsn.mcabstract.MCCommandSender;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCCommandSender;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCPlayer;

public class BukkitCommandExecutor implements CommandExecutor {
	
	private final Plugin plugin;
	private final MCCommand command;
	
	public BukkitCommandExecutor(Plugin plugin, MCCommand command) {
		this.plugin = plugin;
		this.command = command;
	}

	@Override
	public boolean onCommand(CommandSender rawSender, Command rawCommand, String label, String[] args) {
		CommandArguments arguments = new BukkitCommandArguments(args, 0);
		MCCommandSender sender;
		if (rawSender instanceof Player) {
			sender = new BukkitMCPlayer(plugin, (Player) rawSender);
		} else {
			sender = new BukkitMCCommandSender(plugin, rawSender);
		}
		
		try {
			if (command instanceof PlayerCommand) {
				if (!(sender instanceof MCPlayer)) {
					sender.sendLocalizedMessage("player.noconsole");
					return false; //TODO
				}
				return ((PlayerCommand) command).execute((MCPlayer) sender, arguments);
			} else if (command instanceof GeneralCommand) {
				return ((GeneralCommand) command).execute(sender, arguments);
			}
		} catch (InvalidArgumentException e) {
			return false;
		} catch (LocalizedException e) {
			sender.sendLocalizedMessage(e.getLabel(), e.getFillins());
		}
		
		return false;
	}

}
