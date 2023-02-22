package everyos.plugin.fsn.argument;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsArgumentReader implements ArgumentReader<String> {
	
	private final String[] options;

	public SuggestionsArgumentReader(String[] options) {
		this.options = options;
	}

	@Override
	public String read(String value) {
		return value;
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
