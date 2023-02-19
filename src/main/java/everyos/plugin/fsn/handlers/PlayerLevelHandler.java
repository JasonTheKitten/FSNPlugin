package everyos.plugin.fsn.handlers;

import java.util.ArrayList;
import java.util.List;

import everyos.plugin.fsn.handlers.level.LevelUpListener;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLevelChangeEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLevelChangeEventListener;
import everyos.plugin.fsn.mcabstract.stats.PlayerStats;

public class PlayerLevelHandler implements MCPlayerDeathEventListener, MCPlayerLevelChangeEventListener, MCPlayerJoinEventListener {
	
	private static final float DEFAULT_MAX_HEALTH = 100;
	private static final float DEFAULT_DEFENSE = 50;
	private static final float DEFAULT_MAX_MANA = 150;
	
	private final List<LevelUpListener> listeners = new ArrayList<>();

	@Override
	public void onPlayerDeathEvent(MCPlayerDeathEvent event) {
		event.setXPKeepLevel(true);
	}

	@Override
	public void onPlayerLevelChangeEvent(MCPlayerLevelChangeEvent event) {
		float oldMultiplier = (float) Math.pow(1.1, event.getOldLevel());
		float newMultiplier = (float) Math.pow(1.1, event.getNewLevel());
		
		MCPlayer player = event.getPlayer();
		PlayerStats stats = player.getStats();
		
		resizeCurrentStats(oldMultiplier, newMultiplier, stats);
		updateMaxStats(newMultiplier, stats);
		
		for (LevelUpListener listener: listeners) {
			listener.onLevelUp(player);
		}
	}

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
	
	public void addLevelUpListener(LevelUpListener listener) {
		listeners.add(listener);
	}
	
	public void removeLevelUpListener(LevelUpListener listener) {
		listeners.remove(listener);
	}
	
	private void resizeCurrentStats(float oldMultiplier, float newMultiplier, PlayerStats stats) {
		float scale = 1 / oldMultiplier * newMultiplier;
		resizeStat(stats, scale, PlayerStats.CURRENT_HEALTH_STAT);
		resizeStat(stats, scale, PlayerStats.CURRENT_MANA_STAT);
	}

	private void resizeStat(PlayerStats stats, float scale, String statName) {
		stats.adjust(statName).set(stats.getByName(statName) * scale);
	}

	private void updateMaxStats(float multiplier, PlayerStats stats) {
		float newMaxHealth = DEFAULT_MAX_HEALTH * multiplier;
		float newDefense = DEFAULT_DEFENSE * multiplier;
		float newMaxMana = DEFAULT_MAX_MANA * multiplier;
		stats.adjust(PlayerStats.MAX_HEALTH_STAT).set(newMaxHealth);
		stats.adjust(PlayerStats.DEFENSE_STAT).set(newDefense);
		stats.adjust(PlayerStats.MAX_MANA_STAT).set(newMaxMana);
	}

}
