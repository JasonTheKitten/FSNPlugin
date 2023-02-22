package everyos.plugin.fsn.commands.itemstats;

import java.util.List;

import everyos.plugin.fsn.argument.InvalidArgumentException;
import everyos.plugin.fsn.argument.SubcommandArgumentReader;
import everyos.plugin.fsn.commands.itemstats.ItemStatsAdjustCommand.Option;
import everyos.plugin.fsn.mcabstract.command.CommandArguments;
import everyos.plugin.fsn.mcabstract.command.PlayerCommand;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;

public class ItemStatsCommand implements PlayerCommand {

	private SubcommandArgumentReader subcommandArgumentReader = new SubcommandArgumentReader();
	
	public ItemStatsCommand() {
		subcommandArgumentReader.addCommand("add", new ItemStatsAdjustCommand(Option.ADD));
		subcommandArgumentReader.addCommand("set", new ItemStatsAdjustCommand(Option.SET));
		subcommandArgumentReader.addCommand("sub", new ItemStatsAdjustCommand(Option.SUB));
		subcommandArgumentReader.addCommand("get", new ItemStatsGetCommand());
		subcommandArgumentReader.addCommand("summary", new ItemStatsSummaryCommand());
		subcommandArgumentReader.addCommand("reset", new ItemStatsResetCommand());
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
