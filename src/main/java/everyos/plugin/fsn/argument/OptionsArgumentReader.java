package everyos.plugin.fsn.argument;

import java.util.ArrayList;
import java.util.List;

public class OptionsArgumentReader implements ArgumentReader<String> {
	
	private final String[] options;

	public OptionsArgumentReader(String[] options) {
		this.options = options;
	}

	@Override
	public String read(String value) {
		for (String option: options) {
			if (option.equals(value)) {
				return option;
			}
		}
		
		throw new InvalidArgumentException();
	}

	@Override
	public List<String> autocomplete(String value) {
		List<String> autocompletes = new ArrayList<>();
		for (String option: options) {
			if (option.toLowerCase().contains(value.toLowerCase())) {
				autocompletes.add(option);
			}
		}
		
		return autocompletes;
	}

}
