package everyos.plugin.fsn.mcabstract.imp.bukkit.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCEntity;
import everyos.plugin.fsn.mcabstract.event.MCEntityDamageEvent;
import everyos.plugin.fsn.mcabstract.event.MCEntityDamageEventListener;
import everyos.plugin.fsn.mcabstract.imp.bukkit.entity.BukkitMCEntityFactory;

public class EntityDamageEventListenerWrapper implements Listener {

	private final Plugin plugin;
	private final MCEntityDamageEventListener listener;

	public EntityDamageEventListenerWrapper(Plugin plugin, MCEntityDamageEventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		listener.onEntityDamageEvent(convertEvent(e));
	}

	private MCEntityDamageEvent convertEvent(EntityDamageEvent e) {
		return new MCEntityDamageEvent() {
			
			@Override
			public MCEntity getEntity() {
				return BukkitMCEntityFactory.of(plugin, e.getEntity());
			}
			
			@Override
			public void preventDefault() {
				e.setCancelled(true);
			}
			
			@Override
			public float getRawDamage() {
				return (float) e.getDamage();
			}
			
		};
	}
	
}
