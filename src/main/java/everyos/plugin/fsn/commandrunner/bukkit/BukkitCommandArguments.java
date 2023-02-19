package everyos.plugin.fsn.commandrunner.bukkit;

import java.util.List;

import everyos.plugin.fsn.commandrunner.ArgumentReader;
import everyos.plugin.fsn.commandrunner.CommandArguments;

public class BukkitCommandArguments implements CommandArguments {

	private final String[] args;
	private final int offset;

	public BukkitCommandArguments(String[] args, int offset) {
		this.args = args;
		this.offset = offset;
	}

	@Override
	public <T> T read(int index, ArgumentReader<T> reader) {
		return reader.read(args[index + offset]);
	}

	@Override
	public <T> List<String> autocomplete(int index, ArgumentReader<T> reader) {
		return reader.autocomplete(args[index + offset]);
	}

}