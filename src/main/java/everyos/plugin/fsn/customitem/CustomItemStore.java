package everyos.plugin.fsn.customitem;

public interface CustomItemStore {

	CustomItem[] getCustomItems();
	
	void removeCustomItem(String name);
	
	CustomItem createCustomItem(CustomItemCreationOptions options);
	
	boolean hasCustomItem(String name);
	
}
