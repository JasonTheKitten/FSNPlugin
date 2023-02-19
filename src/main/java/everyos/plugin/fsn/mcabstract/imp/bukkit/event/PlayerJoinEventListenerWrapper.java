package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerJoinEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.BukkitMCPlayer;

public class PlayerJoinEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCPlayerJoinEventListener listener;

	public PlayerJoinEventListenerWrapper(Plugin plugin, MCPlayerJoinEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		listener.onPlayerJoinEvent(convertEvent(e));
	}

	private MCPlayerJoinEvent convertEvent(PlayerJoinEvent e) {
		return new MCPlayerJoinEvent() {
			
			@Override
			public MCPlayer getPlayer() {
				return new BukkitMCPlayer(plugin, e.getPlayer());
			}
			
		};
	}
	
}
