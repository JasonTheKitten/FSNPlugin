package everyos.plugin.fsn.commandrunner;

import java.util.List;

public interface CommandArguments {

	<T> T read(int index, ArgumentReader<T> reader);
	
	<T> List<String> autocomplete(int index, ArgumentReader<T> reader);
	
}
