package everyos.plugin.fsn.stats.util;

import everyos.plugin.fsn.mcabstract.entity.MCEntity;
import everyos.plugin.fsn.mcabstract.stats.StatsContainer;

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

	public static float sumStats(MCEntity entity, String type) {
		StatsContainer[] statsProviders = new StatsContainer[] {
			entity.getStats()	
		};
		
		float total = 0;
		for (StatsContainer provider: statsProviders) {
			total += provider.getByName(type);
		}
		
		return total;
	}
	
}
