package everyos.plugin.fsn.mcabstract.bukkit;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.stats.ItemStats;
import everyos.plugin.fsn.stats.StatAdjustor;

public class BukkitItemStats implements ItemStats {

	private final Plugin plugin;
	private final PersistentDataContainer itemData;

	public BukkitItemStats(Plugin plugin, PersistentDataContainer itemData) {
		this.plugin = plugin;
		this.itemData = itemData;
	}

	@Override
	public float getByName(String statName) {
		return itemData.get(new NamespacedKey(plugin, statName + "_stat"), PersistentDataType.FLOAT);
	}

	@Override
	public StatAdjustor adjust(String statName) {
		
		NamespacedKey key = new NamespacedKey(plugin, statName + "_stat");
		PersistentDataType<Float, Float> type = PersistentDataType.FLOAT;
		
		return new StatAdjustor() {
			
			@Override
			public void set(float amount) {
				itemData.set(key, type, amount);
			}
			
			@Override
			public void increase(float amount) {
				float oldAmount = itemData.get(key, type);
				itemData.set(key, type, oldAmount + amount);
			}
			
			@Override
			public void decrease(float amount) {
				float oldAmount = itemData.get(key, type);
				float newAmount = Math.max(oldAmount - amount, 0);
				itemData.set(key, type, newAmount);
			}
		};
	}

}
