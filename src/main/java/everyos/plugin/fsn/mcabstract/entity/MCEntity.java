package everyos.plugin.fsn.mcabstract.entity;

import java.util.UUID;

import everyos.plugin.fsn.mcabstract.stats.EntityStats;

public interface MCEntity {

	UUID getUUID();
	
	EntityStats getStats();
	
	boolean hasInterface(Class<? extends MCEntityInterface> intfClass);

	<T extends MCEntityInterface> T getInterface(Class<T> intfClass);
	
}
