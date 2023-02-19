package everyos.plugin.fsn.stats;

public interface StatAdjustor {

	void increase(float amount);
	
	void decrease(float amount);
	
	void set(float amount);
	
}
