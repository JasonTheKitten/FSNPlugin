package everyos.plugin.fsn.commandrunner.argument;

import java.util.ArrayList;
import java.util.List;

public class OptionsArgumentReader implements ArgumentReader<String> {
	
	private String[] options;

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
			if (option.contains(value)) {
				autocompletes.add(option);
			}
		}
		
		return autocompletes;
	}

}
