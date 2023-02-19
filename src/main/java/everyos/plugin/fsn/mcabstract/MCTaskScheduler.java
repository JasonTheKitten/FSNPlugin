package everyos.plugin.fsn.mcabstract;

public interface MCTaskScheduler {

	MCTaskHandle addRepeatingTask(Runnable task, int delay, int period);
	
}
