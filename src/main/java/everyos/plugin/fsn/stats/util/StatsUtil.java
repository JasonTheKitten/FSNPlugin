package everyos.plugin.fsn.stats.util;

public final class StatsUtil {

	private StatsUtil() {}
	
	public static String generateDisplayName(String statName) {
		boolean lastCharIsWhitespace = true;
		StringBuilder nameBuilder = new StringBuilder(statName.length());
		
		for (int ch: statName.codePoints().toArray()) {
			if (ch == '_') {
				lastCharIsWhitespace = true;
				nameBuilder.append(' ');
			} else if (lastCharIsWhitespace) {
				nameBuilder.appendCodePoint(Character.toUpperCase(ch));
				lastCharIsWhitespace = false;
			} else {
				nameBuilder.appendCodePoint(ch);
			}
		}
		return nameBuilder.toString();
	}
	
}
