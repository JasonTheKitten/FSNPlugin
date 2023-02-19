package everyos.plugin.fsn.stats;

public interface StatCalculator {

	AttackResults calculateAttackDamageDelt(ItemStats attackStats);
	
	DamageResults calculateAttackDamageReceived(ItemStats defenseStats, AttackResults deltAttack);
	
	MineResults calculatePickDamageDelt(ItemStats pickStats);
	
}
