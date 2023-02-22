package everyos.plugin.fsn.mcabstract.imp.bukkit;

import org.bukkit.Material;

import everyos.plugin.fsn.mcabstract.MCMaterial;

public class BukkitMCMaterial implements MCMaterial {

	private final Material material;

	public BukkitMCMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String getName() {
		return material.toString();
	}

}
