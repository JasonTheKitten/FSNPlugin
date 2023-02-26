package everyos.plugin.fsn.customitem.imp;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;
import dev.mccue.json.JsonWriteOptions;
import everyos.plugin.fsn.customitem.CustomItem;
import everyos.plugin.fsn.customitem.CustomItemCreationOptions;
import everyos.plugin.fsn.customitem.CustomItemStore;

public class JSONFileCustomItemStore implements CustomItemStore {
	
	private final List<JSONCustomItemImp> items;
	
	private final File source;

	public JSONFileCustomItemStore(File source) {
		this.source = source;
		this.items = loadConfig();
	}

	@Override
	public CustomItem[] getCustomItems() {
		return items.toArray(new CustomItem[0]);
	}
	
	@Override
	public CustomItem createCustomItem(CustomItemCreationOptions options) {
		JSONCustomItemImp item = new JSONCustomItemImp(options);
		items.add(item);
		regenerateConfig();
		return item;
	}

	@Override
	public void removeCustomItem(String name) {
		items.removeIf(item -> item.getName().equals(name));
		regenerateConfig();
	}

	@Override
	public boolean hasCustomItem(String name) {
		for (CustomItem item: items) {
			if (item.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	private List<JSONCustomItemImp> loadConfig() {
		try (Reader inputReader = Files.newBufferedReader(source.toPath())) {
			Json json = Json.read(inputReader);
			List<JSONCustomItemImp> customItems = new ArrayList<>();
			customItems.addAll(JsonDecoder.array(json, JSONCustomItemImp::fromJson));
			return customItems;
		} catch (NoSuchFileException e) {
			return List.of();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void regenerateConfig() {
		Json encoded = Json.of(items);
		JsonWriteOptions options = new JsonWriteOptions()
			.withIndentation(4);
		try (Writer outputWriter = Files.newBufferedWriter(source.toPath())) {
			Json.write(encoded, outputWriter, options);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
