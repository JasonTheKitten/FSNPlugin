package everyos.plugin.fsn.imp;

import java.io.File;

import everyos.plugin.fsn.FSNInstance;
import everyos.plugin.fsn.commands.customitems.CustomItemsCommand;
import everyos.plugin.fsn.commands.itemstats.ItemStatsCommand;
import everyos.plugin.fsn.customitem.CustomItemStore;
import everyos.plugin.fsn.customitem.imp.JSONFileCustomItemStore;
import everyos.plugin.fsn.handlers.EntityDamageHandler;
import everyos.plugin.fsn.handlers.PlayerLevelHandler;
import everyos.plugin.fsn.handlers.PlayerStatDisplayUpdateHandler;
import everyos.plugin.fsn.localization.LocalizationProvider;
import everyos.plugin.fsn.localization.imp.LocalizationProviderImp;
import everyos.plugin.fsn.mcabstract.MCPluginBase;
import everyos.plugin.fsn.mcabstract.command.CommandRegistry;

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
		CustomItemStore customItemStore = createCustomItemStore();
		
		CommandRegistry commandRegistry = plugin.getCommandRegistry();
		commandRegistry.addCommand("itemstats", new ItemStatsCommand());
		commandRegistry.addCommand("customitem", new CustomItemsCommand(customItemStore));
	}

	private void registerListeners() {
		PlayerLevelHandler levelHandler = new PlayerLevelHandler();
		plugin.registerEventListener(new PlayerStatDisplayUpdateHandler(plugin, levelHandler));
		plugin.registerEventListener(levelHandler);
		plugin.registerEventListener(new EntityDamageHandler());
	}
	
	private CustomItemStore createCustomItemStore() {
		File source = new File(plugin.getSaveFolder() + "/customitems.json");
		return new JSONFileCustomItemStore(source);
	}

}
