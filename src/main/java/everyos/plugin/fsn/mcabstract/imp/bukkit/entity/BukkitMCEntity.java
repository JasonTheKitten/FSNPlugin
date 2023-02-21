package everyos.plugin.fsn.mcabstract.imp.bukkit.entity;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCEntity;
import everyos.plugin.fsn.mcabstract.entity.MCEntityInterface;
import everyos.plugin.fsn.mcabstract.imp.bukkit.stats.BukkitEntityStats;
import everyos.plugin.fsn.mcabstract.stats.EntityStats;

public class BukkitMCEntity implements MCEntity {

	private final Plugin plugin;
	private final Entity entity;
	private final BukkitMCEntityInterfaceProvider interfaceProvider;

	public BukkitMCEntity(Plugin plugin, Entity entity) {
		this.plugin = plugin;
		this.entity = entity;
		this.interfaceProvider = new BukkitMCEntityInterfaceProvider(entity);
	}

	@Override
	public UUID getUUID() {
		return entity.getUniqueId();
	}

	@Override
	public EntityStats getStats() {
		return BukkitEntityStats.getStats(plugin, entity);
	}
	
	@Override
	public boolean hasInterface(Class<? extends MCEntityInterface> intfClass) {
		return interfaceProvider.hasInterface(intfClass);
	}

	@Override
	public <T extends MCEntityInterface> T getInterface(Class<T> intfClass) {
		return interfaceProvider.getInterface(intfClass);
	}

}
