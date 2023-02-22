package everyos.plugin.fsn.customitem.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import everyos.plugin.fsn.customitem.CustomItem;
import everyos.plugin.fsn.customitem.CustomItemCreationOptions;
import everyos.plugin.fsn.customitem.CustomItemStore;

public class JSONFileCustomItemStore implements CustomItemStore {
	
	private final List<CustomItem> items = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private final File source;

	public JSONFileCustomItemStore(File source) {
		this.source = source;
		
	}

	@Override
	public CustomItem[] getCustomItems() {
		return items.toArray(new CustomItem[0]);
	}
	
	@Override
	public CustomItem createCustomItem(CustomItemCreationOptions options) {
		regenerateConfig();
		CustomItem item = new CustomItemImp(options);
		items.add(item);
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

	private void regenerateConfig() {
		// TODO Auto-generated method stub
		
	}
	
}
