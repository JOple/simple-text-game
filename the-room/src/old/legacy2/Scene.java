package old.legacy2;

public interface Scene<C> {
	
	String getID();
	
	void perform(String command, C context);
	
	void onSceneEntry(C context);
	void onSceneExit(C context);
}
