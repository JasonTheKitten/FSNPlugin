package everyos.plugin.fsn.handlers;

import everyos.plugin.fsn.handlers.statdisplay.PlayerStatDisplayTracker;
import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;

public class PlayerStatDisplayUpdateHandler implements MCPlayerJoinEventListener {
	
	private final MCPluginBase plugin;
	
	public PlayerStatDisplayUpdateHandler(MCPluginBase plugin) {
		this.plugin = plugin;
	}

	@Override
	public void onPlayerJoinEvent(MCPlayerJoinEvent event) {
		MCPlayer player = event.getPlayer();
		new PlayerStatDisplayTracker(plugin, player).start();
	}

}
