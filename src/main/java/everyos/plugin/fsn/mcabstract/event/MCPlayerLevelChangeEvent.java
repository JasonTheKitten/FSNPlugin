package everyos.plugin.fsn.mcabstract.event;

public interface MCPlayerLevelChangeEvent extends MCPlayerEvent {

	int getNewLevel();

	int getOldLevel();

}
