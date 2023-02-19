package everyos.plugin.fsn;

import everyos.plugin.fsn.localization.LocalizationProvider;

public interface FSNInstance {

	LocalizationProvider getDefaultLocalizationProvider();

	void quit();
	
}
