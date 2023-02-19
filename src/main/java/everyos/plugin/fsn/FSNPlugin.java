package everyos.plugin.fsn;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import everyos.plugin.fsn.commandrunner.CommandRegistry;
import everyos.plugin.fsn.commandrunner.bukkit.BukkitMCCommandRegistry;
import everyos.plugin.fsn.commands.stats.StatsCommand;
import everyos.plugin.fsn.imp.FSNInstanceImp;

public class FSNPlugin extends JavaPlugin {

	private final Logger logger;
	
	private FSNInstanceImp instance;
	
	public FSNPlugin() {
		this.logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void onEnable() {
		this.instance = new FSNInstanceImp(this);
		
		CommandRegistry registry = new BukkitMCCommandRegistry(this);
		registry.addCommand("stats", new StatsCommand());
		
		logger.info("Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		instance.quit();
		this.instance = null;
		logger.info("Plugin disabled!");
	}
	
	public FSNInstance getCurrentInstance() {
		return instance;
	}
	
}
