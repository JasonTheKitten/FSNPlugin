package everyos.plugin.fsn.handlers.statdisplay;

import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.MCTaskHandle;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEventListener;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;
import everyos.plugin.fsn.mcabstract.stats.PlayerStats;
import everyos.plugin.fsn.mcabstract.stats.StatsChangeListener;
import everyos.plugin.fsn.mcabstract.stats.StatsContainer;

public class PlayerStatDisplayTracker {
	
	private static final String padding = "    ";
	private static final int LEVEL_UP_DISPLAY_DURATION = 4000;
	
	private final MCPluginBase plugin;
	private final MCPlayer player;
	
	private LevelUpInfo previousLevelUp;
	private LevelUpInfo currentLevelUp;

	public PlayerStatDisplayTracker(MCPluginBase plugin, MCPlayer player) {
		this.plugin = plugin;
		this.player = player;
	}
	
	public void start() {
		this.previousLevelUp = computeCurrentLevelUpInfo();
		registerChangeListeners();
	}
	
	private void registerChangeListeners() {
		StatsChangeListener listener = change -> updatePlayerStatDisplay();
		player.getStats().addChangeListener(listener);
		MCTaskHandle taskHandle = plugin
			.getTaskScheduler()
			.addRepeatingTask(() -> updatePlayerStatDisplay(), 0, 20);	
		plugin.registerEventListener((MCPlayerLeaveEventListener) leaveEvent -> {
			if (!(leaveEvent.getPlayer().getUUID().equals(player.getUUID()))) {
				return;
			}
			taskHandle.cancel();
			player.getStats().removeEventListener(listener);
		});
	}

	private void updatePlayerStatDisplay() {
		detectLevelUp();
		if (currentLevelUp != null) {
			showLevelUpStatDisplay();
		} else {
			showNormalStatDisplay();
		}
	}

	private void detectLevelUp() {
		int playerLevel = player.getXPBar().getLevel();
		if (currentLevelUp != null && playerLevel != currentLevelUp.level()) {
			previousLevelUp = currentLevelUp;
			currentLevelUp = null;
		}
		if (currentLevelUp == null && previousLevelUp.level() != playerLevel) {
			currentLevelUp = computeCurrentLevelUpInfo();
		}
		if (currentLevelUp != null && System.currentTimeMillis() - currentLevelUp.time() > LEVEL_UP_DISPLAY_DURATION) {
			previousLevelUp = currentLevelUp;
			currentLevelUp = null;
		}
	}
	
	private void showLevelUpStatDisplay() {
		int healthChange = (int) (currentLevelUp.maxHealth() - previousLevelUp.maxHealth());
		int defenseChange = (int) (currentLevelUp.defense() - previousLevelUp.defense());
		int manaChange = (int) (currentLevelUp.maxMana() - previousLevelUp.maxMana());
		
		// TODO: Localize text
		String displayBar =
			"&2Level Up!" + padding +
			"&c+" + healthChange + " \u2764" + padding +
			"&a+" + defenseChange + " \u2748" + padding +
			"&b+" + manaChange + " \u270E";
		player.setDisplayBarText(displayBar);
	}

	private void showNormalStatDisplay() {
		PlayerStats playerStats = player.getStats();
		int maxHealth = (int) playerStats.getByName(PlayerStats.MAX_HEALTH_STAT);
		int currentHealth = (int) playerStats.getByName(PlayerStats.CURRENT_HEALTH_STAT);
		int totalDefense = (int) getPlayerTotalDefense();
		int currentMana = (int) playerStats.getByName(PlayerStats.CURRENT_MANA_STAT);
		int maxMana = (int) playerStats.getByName(PlayerStats.MAX_MANA_STAT);
		
		// TODO: Localize text
		String displayBar =
			"&c" + currentHealth + "/" + maxHealth + " \u2764" + padding +
			"&a" + totalDefense + " \u2748 Defense" + padding +
			"&b" + currentMana + "/" + maxMana + " \u270E Mana";
		player.setDisplayBarText(displayBar);
	}

	private float getPlayerTotalDefense() {
		return sumPlayerStats(ItemStats.DEFENSE_STAT);
	}

	private float sumPlayerStats(String type) {
		StatsContainer[] statsProviders = new StatsContainer[] {
			player.getStats()	
		};
		
		float total = 0;
		for (StatsContainer provider: statsProviders) {
			total += provider.getByName(type);
		}
		
		return total;
	}
	
	private LevelUpInfo computeCurrentLevelUpInfo() {
		PlayerStats playerStats = player.getStats();
		int maxHealth = (int) playerStats.getByName(PlayerStats.MAX_HEALTH_STAT);
		int defense = (int) playerStats.getByName(PlayerStats.DEFENSE_STAT);
		int maxMana = (int) playerStats.getByName(PlayerStats.MAX_MANA_STAT);
		
		return new LevelUpInfo(
			System.currentTimeMillis(),
			player.getXPBar().getLevel(),
			maxHealth,
			defense,
			maxMana);
	}

	private static record LevelUpInfo(
		long time, int level, float maxHealth, float defense, float maxMana
	) {}

}
