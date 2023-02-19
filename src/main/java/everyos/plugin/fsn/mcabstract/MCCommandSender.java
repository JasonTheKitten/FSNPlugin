package everyos.plugin.fsn.mcabstract;

import everyos.plugin.fsn.localization.LocalizationProvider;

public interface MCCommandSender {
	
	LocalizationProvider getLocalizationProvider();

	void sendLocalizedMessage(String string, String... fillins);
	
	void sendRawMessage(String string);

}
