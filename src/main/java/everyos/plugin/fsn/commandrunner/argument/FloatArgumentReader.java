package everyos.plugin.fsn.commandrunner.argument;

import java.util.List;

public class FloatArgumentReader implements ArgumentReader<Float> {

	@Override
	public Float read(String value) {
		try {
			return Float.valueOf(value);
		} catch (NumberFormatException e) {
			throw new InvalidArgumentException();
		}
	}

	@Override
	public List<String> autocomplete(String value) {
		return List.of();
	}

}
