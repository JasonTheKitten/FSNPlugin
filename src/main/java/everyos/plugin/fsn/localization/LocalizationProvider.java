package everyos.plugin.fsn.localization;

public interface LocalizationProvider {

	String localize(String string, String... fillins);
	
}
