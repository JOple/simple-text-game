package old.legacy.scenes;

public interface Scene {

	String getName();
	void onEntry();
	void onExit();
	void onAction(String command);
}
