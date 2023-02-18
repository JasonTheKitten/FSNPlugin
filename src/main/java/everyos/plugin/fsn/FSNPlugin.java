package everyos.plugin.fsn;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import everyos.plugin.fsn.imp.FSNInstanceImp;

public class FSNPlugin extends JavaPlugin {

	private final Logger logger;
	
	private FSNInstanceImp instance;
	
	public FSNPlugin() {
		this.logger = Logger.getLogger(getClass().toGenericString());
	}

	@Override
	public void onEnable() {
		this.instance = new FSNInstanceImp();
		logger.info("Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		instance.quit();
		this.instance = null;
		logger.info("Plugin disabled!");
	}
	
}
