package everyos.plugin.fsn.mcabstract.imp.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.stats.ItemStats;
import everyos.plugin.fsn.mcabstract.stats.StatAdjustor;
import everyos.plugin.fsn.stats.util.StatsUtil;

public class BukkitItemStats implements ItemStats {

	private final Plugin plugin;
	private final ItemStack itemStack;

	public BukkitItemStats(Plugin plugin, ItemStack itemStack) {
		this.plugin = plugin;
		this.itemStack = itemStack;
	}

	@Override
	public float getByName(String statName) {
		PersistentDataContainer itemData = itemStack.getItemMeta().getPersistentDataContainer();
		Float stat = itemData.get(new NamespacedKey(plugin, statName + "_stat"), PersistentDataType.FLOAT);
		return stat == null ? 0 : stat;
	}

	@Override
	public boolean isSet(String statName) {
		PersistentDataContainer itemData = itemStack.getItemMeta().getPersistentDataContainer();
		return itemData.has(new NamespacedKey(plugin, statName + "_stat"), PersistentDataType.FLOAT);
	}
	
	@Override
	public StatAdjustor adjust(String statName) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();
		
		NamespacedKey key = new NamespacedKey(plugin, statName + "_stat");
		PersistentDataType<Float, Float> type = PersistentDataType.FLOAT;
		
		return new StatAdjustor() {
			
			@Override
			public void set(float amount) {
				itemData.set(key, type, amount);
				updateItemLore(itemMeta, statName, amount);
				itemStack.setItemMeta(itemMeta);
			}
			
			@Override
			public void increase(float amount) {
				Float oldAmount = itemData.get(key, type);
				if (oldAmount == null) {
					oldAmount = 0f;
				}
				
				float newAmount = Math.max(oldAmount + amount, 0);
				itemData.set(key, type, newAmount);
				updateItemLore(itemMeta, statName, newAmount);
				itemStack.setItemMeta(itemMeta);
			}
			
			@Override
			public void decrease(float amount) {
				increase(-amount);
			}

			@Override
			public void reset() {
				itemData.remove(key);
				updateItemLore(itemMeta, statName, 0);
				itemStack.setItemMeta(itemMeta);
			}
		};
	}
	
	protected void updateItemLore(ItemMeta meta, String statName, float newAmount) {
		String statDisplayName = StatsUtil.generateDisplayName(statName);
		List<String> newLore = new ArrayList<>();
		
		if (meta.hasLore()) {
			for (String oldLore: meta.getLore()) {
				if (!oldLore.contains(statDisplayName)) {
					newLore.add(oldLore);
				}
			}
		}
		
		if (newAmount != 0) {
			String prefix = ChatColor.COLOR_CHAR + "6";
			newLore.add(prefix + statDisplayName + ": " + newAmount);
		}
		
		meta.setLore(newLore);
	}

}
