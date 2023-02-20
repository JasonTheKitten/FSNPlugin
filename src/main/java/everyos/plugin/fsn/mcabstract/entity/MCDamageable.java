package everyos.plugin.fsn.mcabstract.entity;

public interface MCDamageable extends MCEntityInterface {

	float getTotalHealth();

	float getCurrentHealth();

	void setCurrentHealth(float health);

}
