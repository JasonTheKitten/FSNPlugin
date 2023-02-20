package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCPlayer;
import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerDeathEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCPlayer;

public class PlayerDeathEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCPlayerDeathEventListener listener;

	public PlayerDeathEventListenerWrapper(Plugin plugin, MCPlayerDeathEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		listener.onPlayerDeathEvent(convertEvent(e));
	}

	private MCPlayerDeathEvent convertEvent(PlayerDeathEvent e) {
		return new MCPlayerDeathEvent() {
			
			@Override
			public MCPlayer getPlayer() {
				return new BukkitMCPlayer(plugin, e.getEntity());
			}

			@Override
			public void setXPKeepLevel(boolean keepLevel) {
				e.setKeepLevel(keepLevel);
			}
			
		};
	}
	
}
