package everyos.plugin.fsn.handlers;

import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.MCTaskHandle;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEventListener;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;
import everyos.plugin.fsn.mcabstract.stats.PlayerStats;
import everyos.plugin.fsn.mcabstract.stats.StatsChangeListener;
import everyos.plugin.fsn.mcabstract.stats.StatsContainer;

public class PlayerStatDisplayUpdateHandler implements MCPlayerJoinEventListener {
	
	private static final String padding = "    ";
	
	private final MCPluginBase plugin;
	
	public PlayerStatDisplayUpdateHandler(MCPluginBase plugin) {
		this.plugin = plugin;
	}

	@Override
	public void onPlayerJoinEvent(MCPlayerJoinEvent event) {
		MCPlayer player = event.getPlayer();
		StatsChangeListener listener = change -> updatePlayerStatDisplay(player);
		player.getStats().addChangeListener(listener);
		MCTaskHandle taskHandle = plugin
			.getTaskScheduler()
			.addRepeatingTask(() -> updatePlayerStatDisplay(player), 0, 20);
		
		plugin.registerEventListener((MCPlayerLeaveEventListener) leaveEvent -> {
			if (!(leaveEvent.getPlayer().getUUID().equals(player.getUUID()))) {
				return;
			}
			taskHandle.cancel();
			player.getStats().removeEventListener(listener);
		});
	}

	private void updatePlayerStatDisplay(MCPlayer player) {
		PlayerStats playerStats = player.getStats();
		int maxHealth = (int) playerStats.getByName(PlayerStats.MAX_HEALTH_STAT);
		int currentHealth = (int) playerStats.getByName(PlayerStats.CURRENT_HEALTH_STAT);
		int totalDefense = (int) getPlayerTotalDefense(player);
		int currentMana = (int) playerStats.getByName(PlayerStats.CURRENT_MANA_STAT);
		int totalMana = (int) playerStats.getByName(PlayerStats.MAX_MANA_STAT);
		
		// TODO: Localize text
		String displayBar =
			"&c" + currentHealth + "/" + maxHealth + " \u2764" + padding +
			"&a" + totalDefense + " \u2748 Defense" + padding +
			"&b" + currentMana + "/" + totalMana + " \u270E Mana";
		player.setDisplayBarText(displayBar);
	}

	private float getPlayerTotalDefense(MCPlayer player) {
		return sumPlayerStats(player, ItemStats.DEFENSE_STAT);
	}

	private float sumPlayerStats(MCPlayer player, String type) {
		StatsContainer[] statsProviders = new StatsContainer[] {
			player.getStats()	
		};
		
		float total = 0;
		for (StatsContainer provider: statsProviders) {
			total += provider.getByName(type);
		}
		
		return total;
	}

}
