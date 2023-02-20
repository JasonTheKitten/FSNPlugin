package everyos.plugin.fsn.commands.itemstats;

import java.util.List;
import java.util.Optional;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.commandrunner.argument.ArgumentReader;
import everyos.plugin.fsn.commandrunner.argument.InvalidArgumentException;
import everyos.plugin.fsn.commandrunner.argument.OptionsArgumentReader;
import everyos.plugin.fsn.localization.LocalizedException;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;

public class ItemStatsGetCommand implements PlayerCommand {
	
	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (!(arguments.length() == 1)) {
			return false;
		}
		
		String statName = arguments.read(0, createOptionsReader(player));
		
		Optional<ItemStats> stats = getStats(player);
		stats.orElseThrow(() -> new LocalizedException("player.handisempty"));
		showStats(player, stats.get(), statName);
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() == 1) {
			return arguments.autocomplete(0, createOptionsReader(player));
		}
		return List.of();
	}
	
	private void showStats(MCPlayer player, ItemStats itemStats, String statName) {
		String stat = String.valueOf(itemStats.getByName(statName));
		player.sendLocalizedMessage("item.statvalue", "value", stat);
	}

	private ArgumentReader<String> createOptionsReader(MCPlayer player) {
		return getStats(player)
			.map(stats -> stats.getAvailableStats())
			.map(names -> new OptionsArgumentReader(names))
			.orElseThrow(() -> new InvalidArgumentException());
	}
	
	private Optional<ItemStats> getStats(MCPlayer player) {
		return player
			.getHotbar()
			.getMainSelectedItemStack()
			.map(stack -> stack.getItemStats());
	}

}
