package everyos.plugin.fsn.mcabstract.imp.bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import everyos.plugin.fsn.mcabstract.stats.PlayerStats;
import everyos.plugin.fsn.mcabstract.stats.StatAdjustor;
import everyos.plugin.fsn.mcabstract.stats.StatsChangeListener;

public class BukkitPlayerStats implements PlayerStats {
	
	private static final Map<UUID, PlayerStats> cache = new HashMap<>();

	private final List<StatsChangeListener> listeners = new ArrayList<>();
	
	private final Plugin plugin;
	private final PersistentDataContainer playerData;

	private BukkitPlayerStats(Plugin plugin, PersistentDataContainer playerData) {
		this.plugin = plugin;
		this.playerData = playerData;
	}

	@Override
	public float getByName(String statName) {
		Float stat = playerData.get(new NamespacedKey(plugin, statName + "_stat"), PersistentDataType.FLOAT);
		return stat == null ? 0 : stat;
	}

	@Override
	public boolean isSet(String statName) {
		return playerData.has(new NamespacedKey(plugin, statName + "_stat"), PersistentDataType.FLOAT);
	}
	
	@Override
	public StatAdjustor adjust(String statName) {
		NamespacedKey key = new NamespacedKey(plugin, statName + "_stat");
		PersistentDataType<Float, Float> type = PersistentDataType.FLOAT;
		
		return new StatAdjustor() {
			
			@Override
			public void set(float amount) {
				playerData.set(key, type, amount);
				triggerListeners(statName);
			}

			@Override
			public void increase(float amount) {
				Float oldAmount = playerData.get(key, type);
				if (oldAmount == null) {
					oldAmount = 0f;
				}
				
				float newAmount = Math.max(oldAmount + amount, 0);
				playerData.set(key, type, newAmount);
				triggerListeners(statName);
			}
			
			@Override
			public void decrease(float amount) {
				increase(-amount);
				triggerListeners(statName);
			}

			@Override
			public void reset() {
				playerData.remove(key);
				triggerListeners(statName);
			}
			
		};
	}

	private void triggerListeners(String statName) {
		for (StatsChangeListener listener: listeners) {
			listener.onStatChanged(statName);
		}
	}
	
	@Override
	public void addChangeListener(StatsChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeEventListener(StatsChangeListener listener) {
		listeners.remove(listener);
	}
	
	public static PlayerStats getStats(Plugin plugin, Player player) {
		return cache.computeIfAbsent(player.getUniqueId(), uuid -> {
			return new BukkitPlayerStats(plugin, player.getPersistentDataContainer());
		});
	}

}
