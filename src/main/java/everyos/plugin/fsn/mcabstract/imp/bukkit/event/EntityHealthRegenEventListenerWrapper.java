package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCEntity;
import everyos.plugin.fsn.mcabstract.event.MCEntityHealthRegenEvent;
import everyos.plugin.fsn.mcabstract.event.MCEntityHealthRegenEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCEntityFactory;

public class EntityHealthRegenEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCEntityHealthRegenEventListener listener;

	public EntityHealthRegenEventListenerWrapper(Plugin plugin, MCEntityHealthRegenEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onEntityHealthRegen(EntityRegainHealthEvent e) {
		listener.onEntityHealthRegenEvent(convertEvent(e));
	}

	private MCEntityHealthRegenEvent convertEvent(EntityRegainHealthEvent e) {
		return new MCEntityHealthRegenEvent() {
			
			@Override
			public MCEntity getEntity() {
				return BukkitMCEntityFactory.of(plugin, e.getEntity());
			}
			
			@Override
			public void preventDefault() {
				e.setCancelled(true);
			}
			
		};
	}
	
}
