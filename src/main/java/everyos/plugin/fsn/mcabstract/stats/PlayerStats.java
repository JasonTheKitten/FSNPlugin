package everyos.plugin.fsn.mcabstract.stats;

public interface PlayerStats extends StatsContainer {
	
	public static final String MAX_HEALTH_STAT = "max_health";
	public static final String CURRENT_HEALTH_STAT = "current_health";
	public static final String MAX_MANA_STAT = "max_mana";
	public static final String CURRENT_MANA_STAT = "current_mana";
	public static final String DEFENSE_STAT = "defense";

	default String[] getAvailableStats() {
		return new String[] {
			MAX_HEALTH_STAT, CURRENT_HEALTH_STAT,
			MAX_MANA_STAT, CURRENT_MANA_STAT,
			DEFENSE_STAT
		};
	}
	
	void addChangeListener(StatsChangeListener listener);

	void removeEventListener(StatsChangeListener listener);
	
}
