package everyos.plugin.fsn.localization;

public class LocalizedException extends RuntimeException {

	private static final long serialVersionUID = 5839570417347028605L;
	
	private final String label;
	private final String[] fillins;
	
	public LocalizedException(String label, String... fillins) {
		this.label = label;
		this.fillins = fillins;
	}

	public String getLabel() {
		return this.label;
	}

	public String[] getFillins() {
		return this.fillins;
	}

}
