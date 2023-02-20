package everyos.plugin.fsn.mcabstract.event;

import everyos.plugin.fsn.mcabstract.entity.MCEntity;

public interface MCEntityHealthRegenEvent extends MCEvent {

	MCEntity getEntity();
	
	void preventDefault();
	
}
