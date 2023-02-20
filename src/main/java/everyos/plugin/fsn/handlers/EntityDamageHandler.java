package everyos.plugin.fsn.handlers;

import everyos.plugin.fsn.mcabstract.entity.MCDamageable;
import everyos.plugin.fsn.mcabstract.entity.MCEntity;
import everyos.plugin.fsn.mcabstract.event.MCEntityDamageEvent;
import everyos.plugin.fsn.mcabstract.event.MCEntityDamageEventListener;
import everyos.plugin.fsn.mcabstract.event.MCEntityHealthRegenEvent;
import everyos.plugin.fsn.mcabstract.event.MCEntityHealthRegenEventListener;
import everyos.plugin.fsn.mcabstract.event.MCPlayerRespawnEvent;
import everyos.plugin.fsn.mcabstract.event.MCPlayerRespawnEventListener;
import everyos.plugin.fsn.mcabstract.stats.EntityStats;
import everyos.plugin.fsn.stats.util.StatsUtil;

public class EntityDamageHandler
	implements MCEntityDamageEventListener, MCEntityHealthRegenEventListener, MCPlayerRespawnEventListener
{

	@Override
	public void onEntityDamageEvent(MCEntityDamageEvent event) {
		MCEntity entity = event.getEntity();
		MCDamageable entityDamageable = entity.getInterface(MCDamageable.class);
		if (entityDamageable == null) {
			return;
		}
		
		float attack = event.getRawDamage();
		float defense = StatsUtil.sumStats(entity, EntityStats.DEFENSE_STAT) / 15;
		float damageDealt = calculateDamageDealt(attack, defense);
		
		entity.getStats().adjust(EntityStats.CURRENT_HEALTH_STAT).decrease(damageDealt);
		
		float oldHealth = entity.getStats().getByName(EntityStats.CURRENT_HEALTH_STAT);
		float maxHealth = entity.getStats().getByName(EntityStats.MAX_HEALTH_STAT);
		float ratio = maxHealth == 0 ? 0 : (oldHealth - damageDealt) / maxHealth;
		
		if (ratio > 0) {
			event.preventDefault();
			entityDamageable.setCurrentHealth(entityDamageable.getTotalHealth() * ratio);
		} else {
			// TODO: Kind of hacky. Needed to preserve xp
			entityDamageable.setCurrentHealth(.01f);
		}
	}
	
	@Override
	public void onEntityHealthRegenEvent(MCEntityHealthRegenEvent event) {
		event.preventDefault();
	}
	
	@Override
	public void onPlayerRespawnEvent(MCPlayerRespawnEvent event) {
		EntityStats playerStats = event.getPlayer().getStats();
		playerStats.adjust(EntityStats.CURRENT_HEALTH_STAT)
			.set(playerStats.getByName(EntityStats.MAX_HEALTH_STAT));
	}
	
	private float calculateDamageDealt(float attack, float defense) {
		float damage;
		if (attack >= defense) {
			damage = attack * 2 - defense;
		} else {
			damage = attack * attack / defense;
		}
		
		return damage;
	}

}
