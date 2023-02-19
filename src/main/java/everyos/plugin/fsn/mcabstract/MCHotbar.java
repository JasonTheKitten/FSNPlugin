package everyos.plugin.fsn.mcabstract;

import java.util.Optional;

public interface MCHotbar extends MCInventory {

	Optional<MCItemStack> getMainSelectedItemStack();
	
}
