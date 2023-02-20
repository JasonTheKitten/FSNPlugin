package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLevelChangeEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerLevelChangeEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCPlayer;

public class PlayerLevelChangeEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCPlayerLevelChangeEventListener listener;

	public PlayerLevelChangeEventListenerWrapper(Plugin plugin, MCPlayerLevelChangeEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent e) {
		listener.onPlayerLevelChangeEvent(convertEvent(e));
	}

	private MCPlayerLevelChangeEvent convertEvent(PlayerLevelChangeEvent e) {
		return new MCPlayerLevelChangeEvent() {
			
			@Override
			public MCPlayer getPlayer() {
				return new BukkitMCPlayer(plugin, e.getPlayer());
			}

			@Override
			public int getNewLevel() {
				return e.getNewLevel();
			}

			@Override
			public int getOldLevel() {
				return e.getOldLevel();
			}
			
		};
	}
	
}
