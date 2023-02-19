package everyos.plugin.fsn.stats;

import everyos.plugin.fsn.mcabstract.stats.ItemStats;

public interface StatCalculator {

	AttackResults calculateAttackDamageDelt(ItemStats attackStats);
	
	DamageResults calculateAttackDamageReceived(ItemStats defenseStats, AttackResults deltAttack);
	
	MineResults calculatePickDamageDelt(ItemStats pickStats);
	
}
