package everyos.plugin.fsn.mcabstract.stats;

public interface ItemStats extends StatsContainer {
	
	public static final String SPEED_STAT = "speed";
	public static final String MIN_CRITICAL_PERCENT_STAT = "min_critical_percent";
	public static final String MAX_CRITICAL_PERCENT_STAT = "max_critical_percent";
	public static final String FORTUNE_STAT = "fortune";
	public static final String DEFENSE_STAT = "defense";
	public static final String CRITICAL_CHANCE_STAT = "critical_chance";
	public static final String ATTACK_POWER_STAT = "attack_power";
	public static final String EVASION_STAT = "evasion";

	default String[] getAvailableStats() {
		return new String[] {
			SPEED_STAT, MIN_CRITICAL_PERCENT_STAT, MAX_CRITICAL_PERCENT_STAT,
			FORTUNE_STAT, DEFENSE_STAT, CRITICAL_CHANCE_STAT, ATTACK_POWER_STAT,
			EVASION_STAT
		};
	}
	
}
