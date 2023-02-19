package everyos.plugin.fsn.mcabstract.imp.bukkit;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import everyos.plugin.fsn.mcabstract.MCTaskHandle;
import everyos.plugin.fsn.mcabstract.MCTaskScheduler;

public class BukkitMCTaskScheduler implements MCTaskScheduler {
	
	private final Plugin plugin;

	public BukkitMCTaskScheduler(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public MCTaskHandle addRepeatingTask(Runnable task, int delay, int period) {
		return new BukkitMCTaskHandle(new BukkitRunnable() {
			@Override
			public void run() {
				task.run();
			}
		}.runTaskTimer(plugin, delay, period));
	}

}
