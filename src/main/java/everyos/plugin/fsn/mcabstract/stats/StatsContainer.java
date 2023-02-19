package everyos.plugin.fsn.mcabstract.stats;

public interface StatsContainer {

	String[] getAvailableStats();
	
	float getByName(String statName);
	
	StatAdjustor adjust(String statName);

	boolean isSet(String statName);
	
}
