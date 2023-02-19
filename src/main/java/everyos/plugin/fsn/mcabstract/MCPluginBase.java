package everyos.plugin.fsn.mcabstract;

import java.io.File;

import everyos.plugin.fsn.commandrunner.CommandRegistry;
import everyos.plugin.fsn.mcabstract.event.MCEventListener;

public interface MCPluginBase {

	CommandRegistry getCommandRegistry();

	File getSaveFolder();

	void registerEventListener(MCEventListener listener);
	
	MCTaskScheduler getTaskScheduler();
	
}
