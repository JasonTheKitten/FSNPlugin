package everyos.plugin.fsn.imp;

import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.FSNInstance;
import everyos.plugin.fsn.localization.LocalizationProvider;
import everyos.plugin.fsn.localization.imp.LocalizationProviderImp;

public class FSNInstanceImp implements FSNInstance {
	
	private final LocalizationProvider defaultLocalizationProvider;
	
	public FSNInstanceImp(Plugin plugin) {
		this.defaultLocalizationProvider = new LocalizationProviderImp(plugin, "en_US");
	}

	public void quit() {
		
	}

	@Override
	public LocalizationProvider getDefaultLocalizationProvider() {
		return this.defaultLocalizationProvider;
	}

}
