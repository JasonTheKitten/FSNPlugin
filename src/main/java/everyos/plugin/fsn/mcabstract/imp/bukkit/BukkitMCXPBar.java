package everyos.plugin.fsn.mcabstract.imp.bukkit;

import org.bukkit.entity.Player;

import everyos.plugin.fsn.mcabstract.MCXPBar;

public class BukkitMCXPBar implements MCXPBar {

	private final Player player;

	public BukkitMCXPBar(Player player) {
		this.player = player;
	}

	@Override
	public int getLevel() {
		return player.getLevel();
	}

}
