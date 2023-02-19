package everyos.plugin.fsn.commands.itemstats;

import java.util.List;
import java.util.Optional;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.PlayerCommand;
import everyos.plugin.fsn.commandrunner.argument.ArgumentReader;
import everyos.plugin.fsn.commandrunner.argument.FloatArgumentReader;
import everyos.plugin.fsn.commandrunner.argument.InvalidArgumentException;
import everyos.plugin.fsn.commandrunner.argument.OptionsArgumentReader;
import everyos.plugin.fsn.localization.LocalizedException;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.stats.ItemStats;
import everyos.plugin.fsn.stats.StatAdjustor;

public class ItemStatsAdjustCommand implements PlayerCommand {
	
	private Option option;

	public ItemStatsAdjustCommand(Option option) {
		this.option = option;
	}

	@Override
	public boolean execute(MCPlayer player, CommandArguments arguments) {
		if (!(arguments.length() == 2)) {
			return false;
		}
		
		String statName = arguments.read(0, createOptionsReader(player));
		float statAmount = arguments.read(1, new FloatArgumentReader());
		
		Optional<ItemStats> stats = getStats(player);
		stats.orElseThrow(() -> new LocalizedException("player.handisempty"));
		updateStats(stats.get(), statName, statAmount);
		player.sendLocalizedMessage(
			"item.statnewvalue",
			"value", String.valueOf(stats.get().getByName(statName)));
		
		return true;
	}

	@Override
	public List<String> autocomplete(MCPlayer player, CommandArguments arguments) {
		if (arguments.length() == 1) {
			return arguments.autocomplete(0, createOptionsReader(player));
		}
		return List.of();
	}
	
	private void updateStats(ItemStats itemStats, String statName, float statAmount) {
		StatAdjustor adjustor = itemStats.adjust(statName);
		switch (option) {
		case ADD:
			adjustor.increase(statAmount);
			break;
		case SET:
			adjustor.set(statAmount);
			break;
		case SUB:
			adjustor.decrease(statAmount);
			break;
		default:
			break;
		}
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

	public static enum Option {
		ADD, SUB, SET
	}

}
