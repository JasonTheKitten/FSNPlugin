package everyos.plugin.fsn.handlers.statdisplay;

import everyos.plugin.fsn.handlers.PlayerLevelHandler;
import everyos.plugin.fsn.handlers.level.LevelUpListener;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.MCTaskHandle;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEventListener;
import everyos.plugin.fsn.mcabstract.stats.EntityStats;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;
import everyos.plugin.fsn.stats.util.StatsUtil;

public class PlayerStatDisplayTracker implements LevelUpListener {
	
	private static final String padding = "    ";
	private static final int LEVEL_UP_DISPLAY_DURATION = 4000;
	
	private final MCPluginBase plugin;
	private final PlayerLevelHandler levelHandler;
	private final MCPlayer player;
	
	private LevelUpInfo previousLevelUp;
	private LevelUpInfo currentLevelUp;

	public PlayerStatDisplayTracker(MCPluginBase plugin, PlayerLevelHandler levelHandler, MCPlayer player) {
		this.plugin = plugin;
		this.levelHandler = levelHandler;
		this.player = player;
	}
	
	public void start() {
		this.previousLevelUp = computeCurrentLevelUpInfo();
		registerChangeListeners();
	}
	
	@Override
	public void onLevelUp(MCPlayer player) {
		if (!(player.getUUID().equals(this.player.getUUID()))) {
			return;
		}
		
		detectLevelUp();
		updatePlayerStatDisplay();
	}
	
	private void registerChangeListeners() {
		levelHandler.addLevelUpListener(this);
		MCTaskHandle taskHandle = plugin
			.getTaskScheduler()
			.addRepeatingTask(() -> updatePlayerStatDisplay(), 0, 20);	
		plugin.registerEventListener((MCPlayerLeaveEventListener) leaveEvent -> {
			if (!(leaveEvent.getPlayer().getUUID().equals(player.getUUID()))) {
				return;
			}
			taskHandle.cancel();
			levelHandler.removeLevelUpListener(this);
		});
	}

	private void updatePlayerStatDisplay() {
		clearOldLevelUp();
		if (currentLevelUp != null) {
			showLevelUpStatDisplay();
		} else {
			showNormalStatDisplay();
		}
	}

	private void clearOldLevelUp() {
		if (currentLevelUp != null && System.currentTimeMillis() - currentLevelUp.time() > LEVEL_UP_DISPLAY_DURATION) {
			previousLevelUp = currentLevelUp;
			currentLevelUp = null;
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
		EntityStats playerStats = player.getStats();
		int maxHealth = (int) playerStats.getByName(EntityStats.MAX_HEALTH_STAT);
		int currentHealth = (int) playerStats.getByName(EntityStats.CURRENT_HEALTH_STAT);
		int totalDefense = (int) getPlayerTotalDefense();
		int currentMana = (int) playerStats.getByName(EntityStats.CURRENT_MANA_STAT);
		int maxMana = (int) playerStats.getByName(EntityStats.MAX_MANA_STAT);
		
		// TODO: Localize text
		String displayBar =
			"&c" + currentHealth + "/" + maxHealth + " \u2764" + padding +
			"&a" + totalDefense + " \u2748 Defense" + padding +
			"&b" + currentMana + "/" + maxMana + " \u270E Mana";
		player.setDisplayBarText(displayBar);
	}

	private float getPlayerTotalDefense() {
		return StatsUtil.sumStats(player, ItemStats.DEFENSE_STAT);
	}
	
	private LevelUpInfo computeCurrentLevelUpInfo() {
		EntityStats playerStats = player.getStats();
		int maxHealth = (int) playerStats.getByName(EntityStats.MAX_HEALTH_STAT);
		int defense = (int) playerStats.getByName(EntityStats.DEFENSE_STAT);
		int maxMana = (int) playerStats.getByName(EntityStats.MAX_MANA_STAT);
		
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
