package everyos.plugin.fsn.commands.itemstats;

import java.util.List;
import java.util.Optional;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.commandrunner.argument.ArgumentReader;
import everyos.plugin.fsn.commandrunner.argument.InvalidArgumentException;
import everyos.plugin.fsn.commandrunner.argument.OptionsArgumentReader;
import everyos.plugin.fsn.localization.LocalizedException;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.stats.ItemStats;

public class ItemStatsResetCommand implements PlayerCommand {

	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() > 1) {
			return false;
		}
		
		Optional<ItemStats> stats = getStats(player);
		stats.orElseThrow(() -> new LocalizedException("player.handisempty"));
		if (arguments.length() == 0) {
			resetAllStats(player, stats.get());
		} else {
			String statName = arguments.read(0, createOptionsReader(player));
			resetSingleStat(player, stats.get(), statName);
		}
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() == 1) {
			return arguments.autocomplete(0, createOptionsReader(player));
		}
		return List.of();
	}
	
	private void resetAllStats(MCPlayer player, ItemStats itemStats) {
		for (String stat: itemStats.getAvailableStats()) {
			itemStats.adjust(stat).reset();
		}
		
		player.sendLocalizedMessage("item.statsreset");
	}
	
	private void resetSingleStat(MCPlayer player, ItemStats itemStats, String statName) {
		itemStats.adjust(statName).reset();
		player.sendLocalizedMessage("item.singlestatreset");
	}
	
	private Optional<ItemStats> getStats(MCPlayer player) {
		return player
			.getHotbar()
			.getMainSelectedItemStack()
			.map(stack -> stack.getItemStats());
	}
	
	private ArgumentReader<String> createOptionsReader(MCPlayer player) {
		return getStats(player)
			.map(stats -> stats.getAvailableStats())
			.map(names -> new OptionsArgumentReader(names))
			.orElseThrow(() -> new InvalidArgumentException());
	}


}
