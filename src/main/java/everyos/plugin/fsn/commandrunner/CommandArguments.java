package everyos.plugin.fsn.commandrunner;

import java.util.List;

import everyos.plugin.fsn.commandrunner.argument.ArgumentReader;

public interface CommandArguments {

	<T> T read(int index, ArgumentReader<T> reader);
	
	<T> List<String> autocomplete(int index, ArgumentReader<T> reader);

	int length();
	
	CommandArguments offset(int i);
	
}
