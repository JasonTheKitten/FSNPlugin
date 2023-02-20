package everyos.plugin.fsn.mcabstract.imp.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.entity.MCEntity;

public final class BukkitMCEntityFactory {

	private BukkitMCEntityFactory() {}
	
	public static MCEntity of(Plugin plugin, Entity entity) {
		if (entity instanceof Player player) {
			return new BukkitMCPlayer(plugin, player);
		} else {
			return new BukkitMCEntity(plugin, entity);
		}
	}
	
}
