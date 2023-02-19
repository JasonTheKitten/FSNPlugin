package everyos.plugin.fsn.imp;

import everyos.plugin.fsn.FSNInstance;
import everyos.plugin.fsn.commandrunner.CommandRegistry;
import everyos.plugin.fsn.commands.itemstats.ItemStatsCommand;
import everyos.plugin.fsn.handlers.PlayerStatDisplayUpdateHandler;
import everyos.plugin.fsn.localization.LocalizationProvider;
import everyos.plugin.fsn.localization.imp.LocalizationProviderImp;
import everyos.plugin.fsn.mcabstract.MCPluginBase;

public class FSNInstanceImp implements FSNInstance {
	
	private final LocalizationProvider defaultLocalizationProvider;
	private final MCPluginBase plugin;
	
	public FSNInstanceImp(MCPluginBase plugin) {
		this.defaultLocalizationProvider = new LocalizationProviderImp(plugin.getSaveFolder(), "en_US");
		this.plugin = plugin;
		registerCommands();
		registerListeners();
	}

	@Override
	public void quit() {
		
	}

	@Override
	public LocalizationProvider getDefaultLocalizationProvider() {
		return this.defaultLocalizationProvider;
	}
	
	private void registerCommands() {
		CommandRegistry commandRegistry = plugin.getCommandRegistry();
		commandRegistry.addCommand("itemstats", new ItemStatsCommand());
	}
	
	private void registerListeners() {
		plugin.registerEventListener(new PlayerStatDisplayUpdateHandler(plugin));
	}

}
