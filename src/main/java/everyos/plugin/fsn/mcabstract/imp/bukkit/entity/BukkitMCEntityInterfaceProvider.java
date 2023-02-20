package everyos.plugin.fsn.mcabstract.imp.bukkit.entity;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

import everyos.plugin.fsn.mcabstract.entity.MCDamageable;
import everyos.plugin.fsn.mcabstract.entity.MCEntityInterface;

public class BukkitMCEntityInterfaceProvider {

	private final Entity entity;

	public BukkitMCEntityInterfaceProvider(Entity entity) {
		this.entity = entity;
	}
	
	public boolean hasInterface(Class<? extends MCEntityInterface> intfClass) {
		return getInterface(intfClass) != null;
	}

	@SuppressWarnings("unchecked")
	public <T extends MCEntityInterface> T getInterface(Class<T> intfClass) {
		if (intfClass == MCDamageable.class && entity instanceof Damageable) {
			return (T) new BukkitMCDamageable((Damageable) entity);
		}
		return null;
	}
	
}
