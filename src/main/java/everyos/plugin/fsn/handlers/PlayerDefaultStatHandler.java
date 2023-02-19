package everyos.plugin.fsn.handlers;

import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;
import everyos.plugin.fsn.mcabstract.stats.PlayerStats;

public class PlayerDefaultStatHandler implements MCPlayerJoinEventListener {
	
	private static float DEFAULT_MAX_HEALTH = 100;
	private static float DEFAULT_DEFENSE = 50;
	private static float DEFAULT_MAX_MANA = 150;

	@Override
	public void onPlayerJoinEvent(MCPlayerJoinEvent event) {
		MCPlayer player = event.getPlayer();
		PlayerStats stats = player.getStats();
		if (!stats.isSet(PlayerStats.MAX_HEALTH_STAT)) {
			stats.adjust(PlayerStats.MAX_HEALTH_STAT).set(DEFAULT_MAX_HEALTH);
			stats.adjust(PlayerStats.CURRENT_HEALTH_STAT).set(DEFAULT_MAX_HEALTH);
		}
		if (!stats.isSet(PlayerStats.DEFENSE_STAT)) {
			stats.adjust(PlayerStats.DEFENSE_STAT).set(DEFAULT_DEFENSE);
		}
		if (!stats.isSet(PlayerStats.MAX_MANA_STAT)) {
			stats.adjust(PlayerStats.MAX_MANA_STAT).set(DEFAULT_MAX_MANA);
			stats.adjust(PlayerStats.CURRENT_MANA_STAT).set(DEFAULT_MAX_MANA);
		}
	}

}
