package everyos.plugin.fsn.commandrunner.argument;

import java.util.List;

public class FloatArgumentReader implements ArgumentReader<Float> {

	@Override
	public Float read(String value) {
		return Float.valueOf(value);
	}

	@Override
	public List<String> autocomplete(String value) {
		return List.of();
	}

}
