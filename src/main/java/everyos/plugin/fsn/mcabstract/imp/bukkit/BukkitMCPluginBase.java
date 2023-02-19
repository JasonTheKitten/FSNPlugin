package everyos.plugin.fsn.mcabstract.imp.bukkit;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import everyos.plugin.fsn.commandrunner.CommandRegistry;
import everyos.plugin.fsn.commandrunner.bukkit.BukkitMCCommandRegistry;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.MCTaskScheduler;
import everyos.plugin.fsn.mcabstract.event.MCEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerJoinEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerLeaveEventListenerWrapper;

public class BukkitMCPluginBase implements MCPluginBase {
	
	private final JavaPlugin plugin;
	private final CommandRegistry commandRegistry;
	private final MCTaskScheduler taskScheduler;
	
	public BukkitMCPluginBase(JavaPlugin plugin) {
		this.plugin = plugin;
		this.commandRegistry = new BukkitMCCommandRegistry(plugin);
		this.taskScheduler = new BukkitMCTaskScheduler(plugin);
	}

	@Override
	public CommandRegistry getCommandRegistry() {
		return this.commandRegistry;
	}

	@Override
	public File getSaveFolder() {
		return plugin.getDataFolder();
	}
	
	@Override
	public void registerEventListener(MCEventListener listener) {
		if (listener instanceof MCPlayerJoinEventListener joinEventListener) {
			addEvent(new PlayerJoinEventListenerWrapper(plugin, joinEventListener));
		}
		if (listener instanceof MCPlayerLeaveEventListener leaveEventListener) {
			addEvent(new PlayerLeaveEventListenerWrapper(plugin, leaveEventListener));
		}
	}
	
	@Override
	public MCTaskScheduler getTaskScheduler() {
		return this.taskScheduler;
	}

	private void addEvent(Listener eventListener) {
		plugin
			.getServer()
			.getPluginManager()
			.registerEvents(eventListener, plugin);
	}

}
