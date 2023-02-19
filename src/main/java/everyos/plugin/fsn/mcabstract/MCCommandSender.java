package everyos.plugin.fsn.mcabstract;

public interface MCCommandSender {

	void sendLocalizedMessage(String string, String... fillins);
	
	void sendRawMessage(String string);

}
