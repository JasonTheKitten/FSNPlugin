package everyos.plugin.fsn.localization.imp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import de.leonhard.storage.Toml;
import everyos.plugin.fsn.localization.LocalizationProvider;

public class LocalizationProviderImp implements LocalizationProvider {

	private Toml config;

	public LocalizationProviderImp(Plugin plugin, String lang) {
		this.config = new Toml(
			"messages",
			plugin.getDataFolder().toString(),
			plugin.getClass().getClassLoader().getResourceAsStream("messages/"+ lang + ".toml"));
	}
	
	@Override
	public String localize(String path, String... fillins) {
		String rawMsg = config.get(path, path);
		String filledMsg = StringSubstitutor.replace(rawMsg, fillinsToMap(fillins), "%", "%");
		String coloredMsg = ChatColor.translateAlternateColorCodes('&', filledMsg);
		
		return coloredMsg;
	}

	private Map<String, String> fillinsToMap(String[] fillins) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < fillins.length; i += 2) {
			map.put(fillins[i], fillins[i + 1]);
		}
		return map;
	}

}
