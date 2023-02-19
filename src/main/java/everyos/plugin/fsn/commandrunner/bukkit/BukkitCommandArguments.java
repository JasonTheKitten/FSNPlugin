package everyos.plugin.fsn.commandrunner.bukkit;

import java.util.List;

import everyos.plugin.fsn.commandrunner.CommandArguments;
import everyos.plugin.fsn.commandrunner.argument.ArgumentReader;

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

	@Override
	public int length() {
		return args.length - offset;
	}

	@Override
	public CommandArguments offset(int i) {
		return new BukkitCommandArguments(args, offset + i);
	}

}
