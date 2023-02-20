package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerRespawnEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerRespawnEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCPlayer;

public class PlayerRespawnEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCPlayerRespawnEventListener listener;

	public PlayerRespawnEventListenerWrapper(Plugin plugin, MCPlayerRespawnEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		listener.onPlayerRespawnEvent(convertEvent(e));
	}

	private MCPlayerRespawnEvent convertEvent(PlayerRespawnEvent e) {
		return new MCPlayerRespawnEvent() {
			
			@Override
			public MCPlayer getPlayer() {
				return new BukkitMCPlayer(plugin, e.getPlayer());
			}
			
		};
	}
	
}
