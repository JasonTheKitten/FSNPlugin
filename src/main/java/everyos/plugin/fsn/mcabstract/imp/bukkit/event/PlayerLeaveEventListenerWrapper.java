package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLeaveEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCPlayer;

public class PlayerLeaveEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCPlayerLeaveEventListener listener;

	public PlayerLeaveEventListenerWrapper(Plugin plugin, MCPlayerLeaveEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		listener.onPlayerLeaveEvent(convertEvent(e));
	}

	private MCPlayerLeaveEvent convertEvent(PlayerQuitEvent e) {
		return new MCPlayerLeaveEvent() {
			
			@Override
			public MCPlayer getPlayer() {
				return new BukkitMCPlayer(plugin, e.getPlayer());
			}
			
		};
	}
	
}
