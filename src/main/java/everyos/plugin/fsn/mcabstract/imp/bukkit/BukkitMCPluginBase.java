package everyos.plugin.fsn.mcabstract.imp.bukkit;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import everyos.plugin.fsn.commandrunner.CommandRegistry;
import everyos.plugin.fsn.commandrunner.bukkit.BukkitMCCommandRegistry;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.MCTaskScheduler;
import everyos.plugin.fsn.mcabstract.event.MCEntityDamageEventListener;
import everyos.plugin.fsn.mcabstract.event.MCEntityHealthRegenEventListener;
import everyos.plugin.fsn.mcabstract.event.MCEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLevelChangeEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerRespawnEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.EntityDamageEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.EntityHealthRegenEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerDeathEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerJoinEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerLeaveEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerLevelChangeEventListenerWrapper;
import everyos.plugin.fsn.mcabstract.imp.bukkit.event.PlayerRespawnEventListenerWrapper;

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
		if (listener instanceof MCPlayerJoinEventListener eventListener) {
			addEvent(new PlayerJoinEventListenerWrapper(plugin, eventListener));
		}
		if (listener instanceof MCPlayerLeaveEventListener eventListener) {
			addEvent(new PlayerLeaveEventListenerWrapper(plugin, eventListener));
		}
		if (listener instanceof MCPlayerDeathEventListener eventListener) {
			addEvent(new PlayerDeathEventListenerWrapper(plugin, eventListener));
		}
		if (listener instanceof MCPlayerLevelChangeEventListener eventListener) {
			addEvent(new PlayerLevelChangeEventListenerWrapper(plugin, eventListener));
		}
		if (listener instanceof MCPlayerRespawnEventListener eventListener) {
			addEvent(new PlayerRespawnEventListenerWrapper(plugin, eventListener));
		}
		if (listener instanceof MCEntityDamageEventListener eventListener) {
			addEvent(new EntityDamageEventListenerWrapper(plugin, eventListener));
		}
		if (listener instanceof MCEntityHealthRegenEventListener eventListener) {
			addEvent(new EntityHealthRegenEventListenerWrapper(plugin, eventListener));
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
