package everyos.plugin.fsn.commands.itemstats;

import java.util.List;
import java.util.Optional;

import org.bukkit.ChatColor;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.localization.LocalizationProvider;
import everyos.plugin.fsn.localization.LocalizedException;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.stats.ItemStats;
import everyos.plugin.fsn.stats.util.StatsUtil;

public class ItemStatsSummaryCommand implements PlayerCommand {

	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() != 0) {
			return false;
		}
		
		Optional<ItemStats> stats = getStats(player);
		stats.orElseThrow(() -> new LocalizedException("player.handisempty"));
		summarizeStats(player, stats.get());
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		return List.of();
	}
	
	private void summarizeStats(MCPlayer player, ItemStats itemStats) {
		StringBuilder message = new StringBuilder(ChatColor.COLOR_CHAR + "2");
		LocalizationProvider provider = player.getLocalizationProvider();
		message.append(provider.localize("item.statsummary"));
		
		boolean statsSet = false;
		for (String stat: itemStats.getAvailableStats()) {
			if (!itemStats.isSet(stat)) {
				continue;
			}
			statsSet = true;
			float statValue = itemStats.getByName(stat);
			message.append("\n" + ChatColor.COLOR_CHAR + "6");
			message.append(StatsUtil.generateDisplayName(stat));
			message.append(": ");
			message.append(statValue);
		}
		
		if (!statsSet) {
			player.sendLocalizedMessage("item.nostatsset");
			return;
		}
		
		player.sendRawMessage(message.toString());
	}
	
	private Optional<ItemStats> getStats(MCPlayer player) {
		return player
			.getHotbar()
			.getMainSelectedItemStack()
			.map(stack -> stack.getItemStats());
	}


}
