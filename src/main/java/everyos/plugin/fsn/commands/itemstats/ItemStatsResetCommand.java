package everyos.plugin.fsn.commands.itemstats;

import java.util.List;
import java.util.Optional;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.localization.LocalizedException;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.stats.ItemStats;

public class ItemStatsResetCommand implements PlayerCommand {

	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() != 0) {
			return false;
		}
		
		Optional<ItemStats> stats = getStats(player);
		stats.orElseThrow(() -> new LocalizedException("player.handisempty"));
		resetStats(player, stats.get());
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		return List.of();
	}
	
	private void resetStats(MCPlayer player, ItemStats itemStats) {
		for (String stat: itemStats.getAvailableStats()) {
			itemStats.adjust(stat).reset();
		}
		
		player.sendLocalizedMessage("item.statsreset");
	}
	
	private Optional<ItemStats> getStats(MCPlayer player) {
		return player
			.getHotbar()
			.getMainSelectedItemStack()
			.map(stack -> stack.getItemStats());
	}


}
