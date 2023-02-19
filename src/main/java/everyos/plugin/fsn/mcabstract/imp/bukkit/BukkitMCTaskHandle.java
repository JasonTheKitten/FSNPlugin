package everyos.plugin.fsn.mcabstract.imp.bukkit;

import org.bukkit.scheduler.BukkitTask;

import everyos.plugin.fsn.mcabstract.MCTaskHandle;

public class BukkitMCTaskHandle implements MCTaskHandle {

	private BukkitTask task;

	public BukkitMCTaskHandle(BukkitTask task) {
		this.task = task;
	}

	@Override
	public void cancel() {
		task.cancel();
	}

}
