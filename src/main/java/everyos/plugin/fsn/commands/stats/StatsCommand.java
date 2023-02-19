package everyos.plugin.fsn.commands.stats;

import java.util.List;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.commandrunner.argument.InvalidArgumentException;
import everyos.plugin.fsn.commandrunner.argument.SubcommandArgumentReader;
import everyos.plugin.fsn.commands.stats.StatsAddCommand.Option;
import everyos.plugin.fsn.mcabstract.MCPlayer;

public class StatsCommand implements PlayerCommand {

	private SubcommandArgumentReader subcommandArgumentReader = new SubcommandArgumentReader();
	
	public StatsCommand() {
		subcommandArgumentReader.addCommand("add", new StatsAddCommand(Option.ADD));
		subcommandArgumentReader.addCommand("set", new StatsAddCommand(Option.SET));
		subcommandArgumentReader.addCommand("sub", new StatsAddCommand(Option.SUB));
		subcommandArgumentReader.addCommand("get", new StatsGetCommand());
	}
	
	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() < 1) {
			throw new InvalidArgumentException();
		}
		
		return ((PlayerCommand) arguments
			.read(0, subcommandArgumentReader))
			.execute(player, arguments.offset(1));
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() == 1) {
			return arguments.autocomplete(0, subcommandArgumentReader);
		} else {
			return ((PlayerCommand) arguments
				.read(0, subcommandArgumentReader))
				.autocomplete(player, arguments.offset(1));
		}
	}

}
