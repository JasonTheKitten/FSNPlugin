package everyos.plugin.fsn.customitem;

public interface AbilitySet {

	Ability[] getAbilities();
	
	boolean hasAbility(Ability ability);
	
	void removeAbilitiy(Ability ability);
	
	void addAbility(Ability ability);
	
}
