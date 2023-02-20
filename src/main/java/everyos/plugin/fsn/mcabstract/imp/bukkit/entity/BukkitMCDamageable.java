package everyos.plugin.fsn.mcabstract.imp.bukkit.entity;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;

import everyos.plugin.fsn.mcabstract.entity.MCDamageable;

public class BukkitMCDamageable implements MCDamageable {

	private Damageable entity;

	public BukkitMCDamageable(Damageable entity) {
		this.entity = entity;
	}
	
	@Override
	public float getTotalHealth() {
		return (float) ((Attributable) entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
	}
	
	@Override
	public float getCurrentHealth() {
		return (float) entity.getHealth();
	}

	@Override
	public void setCurrentHealth(float health) {
		entity.setHealth(health < 0 ? 0 : health);
	}

}
