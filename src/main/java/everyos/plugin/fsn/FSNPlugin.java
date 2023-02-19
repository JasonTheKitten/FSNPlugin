package everyos.plugin.fsn;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import everyos.plugin.fsn.imp.FSNInstanceImp;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCPluginBase;

public class FSNPlugin extends JavaPlugin {

	private final Logger logger;
	
	private FSNInstance instance;
	
	public FSNPlugin() {
		this.logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void onEnable() {
		this.instance = new FSNInstanceImp(new BukkitMCPluginBase(this));	
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
