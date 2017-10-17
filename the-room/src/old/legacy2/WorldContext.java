package old.legacy2;

import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public interface WorldContext {

	Map<String, Object> getInventory();
	
	List<String> getSceneIDs();
	Scene<WorldContext> getScene(String id);
	void addScene(Scene<WorldContext> scene);
	void delScene(String id);
	
	Scene<WorldContext> getCurrentScene();
	void setCurrentScene(String id);
	
	int getGlobalActionCount();
	SceneAction<WorldContext> getGlobalAction(int index);
	void addGlobalAction(SceneAction<WorldContext> action);
	void addGlobalAction(int index, SceneAction<WorldContext> action);
	void delGlobalAction(int index);
	
	BiConsumer<String, WorldContext> getGlobalDefaultAction();
	void setGlobalDefaultAction(BiConsumer<String, WorldContext> globalDefaultAction);
	
	Image getDisplayedImage();
	void setDisplayedImage(Image displayedImage);
	
	String getDisplayedDialogue();
	void setDisplayedDialogue(String displayedDialogue);

	String getInputText();
	void setInputText(String inputText);
}
