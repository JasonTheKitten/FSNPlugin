package everyos.plugin.fsn.localization.imp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import de.leonhard.storage.Toml;
import everyos.plugin.fsn.localization.LocalizationProvider;

public class LocalizationProviderImp implements LocalizationProvider {

	private Toml config;

	public LocalizationProviderImp(File saveFolder, String lang) {
		
		this.config = new Toml(
			"messages/"+ lang,
			saveFolder.toString(),
			getClass().getClassLoader().getResourceAsStream("messages/" + lang + ".toml"));
	}
	
	@Override
	public String localize(String path, String... fillins) {
		String rawMsg = config.get(path, path);
		String filledMsg = StringSubstitutor.replace(rawMsg, fillinsToMap(fillins), "%", "%");
		
		return filledMsg;
	}

	private Map<String, String> fillinsToMap(String[] fillins) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < fillins.length; i += 2) {
			map.put(fillins[i], fillins[i + 1]);
		}
		return map;
	}

}
