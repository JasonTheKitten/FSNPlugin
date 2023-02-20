package everyos.plugin.fsn.handlers;

import everyos.plugin.fsn.handlers.statdisplay.PlayerStatDisplayTracker;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;

public class PlayerStatDisplayUpdateHandler implements MCPlayerJoinEventListener {
	
	private final MCPluginBase plugin;
	private final PlayerLevelHandler levelHandler;
	
	public PlayerStatDisplayUpdateHandler(MCPluginBase plugin, PlayerLevelHandler levelHandler) {
		this.plugin = plugin;
		this.levelHandler = levelHandler;
	}

	@Override
	public void onPlayerJoinEvent(MCPlayerJoinEvent event) {
		MCPlayer player = event.getPlayer();
		new PlayerStatDisplayTracker(plugin, levelHandler, player).start();
	}

}
