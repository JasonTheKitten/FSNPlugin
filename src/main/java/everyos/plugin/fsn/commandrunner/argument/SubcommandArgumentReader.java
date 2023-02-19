package everyos.plugin.fsn.commandrunner.argument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import everyos.plugin.fsn.commandrunner.MCCommand;

public class SubcommandArgumentReader implements ArgumentReader<MCCommand> {
	
	private final Map<String, MCCommand> commands = new HashMap<>();

	@Override
	public MCCommand read(String value) {
		if (!commands.containsKey(value)) {
			throw new InvalidArgumentException();
		}
		
		return commands.get(value);
	}

	@Override
	public List<String> autocomplete(String value) {
		List<String> completions = new ArrayList<>();
		for (String key: commands.keySet()) {
			if (key.contains(value)) {
				completions.add(key);
			}
		}
		
		return completions;
	}

	public void addCommand(String name, MCCommand command) {
		commands.put(name, command);
	}
	
}
