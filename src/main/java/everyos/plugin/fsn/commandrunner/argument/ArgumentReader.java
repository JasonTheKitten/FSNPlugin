package everyos.plugin.fsn.commandrunner.argument;

import java.util.List;

public interface ArgumentReader<T> {

	T read(String value);
	
	List<String> autocomplete(String value);
	
}
