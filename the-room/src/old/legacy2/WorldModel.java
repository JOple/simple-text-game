package old.legacy2;

import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public final class WorldModel implements WorldContext {

	private Map<String, Object> inventory;
	private Map<String, Scene<WorldContext>> scenes;
	private Scene<WorldContext> currentScene;
	private List<SceneAction<WorldContext>> globalActions;
	private BiConsumer<String, WorldContext> globalDefaultAction;
	private Image displayedImage;
	private String displayedDialogue;
	private String inputText;
	
	public WorldModel() {
		inventory = new LinkedHashMap<>();
		scenes = new LinkedHashMap<>();
		globalActions = new ArrayList<>();
	}

	@Override
	public Map<String, Object> getInventory() {
		return inventory;
	}
	
	@Override
	public List<String> getSceneIDs() {
		return new ArrayList<>(scenes.keySet());
	}
	@Override
	public Scene<WorldContext> getScene(String id) {
		return scenes.get(id);
	}
	@Override
	public void addScene(Scene<WorldContext> scene) {
		scenes.put(scene.getID(), scene);
	}
	@Override
	public void delScene(String id) {
		scenes.remove(id);
	}
	
	@Override
	public Scene<WorldContext> getCurrentScene() {
		return currentScene;
	}
	@Override
	public void setCurrentScene(String id) {
		this.currentScene = scenes.get(id);
	}

	@Override
	public int getGlobalActionCount() {
		return globalActions.size();
	}
	@Override
	public SceneAction<WorldContext> getGlobalAction(int index) {
		return globalActions.get(index);
	}
	@Override
	public void addGlobalAction(SceneAction<WorldContext> action) {
		globalActions.add(action);
	}
	@Override
	public void addGlobalAction(int index, SceneAction<WorldContext> action) {
		globalActions.add(index, action);
	}
	@Override
	public void delGlobalAction(int index) {
		globalActions.remove(index);
	}
	
	@Override
	public BiConsumer<String, WorldContext> getGlobalDefaultAction() {
		return globalDefaultAction;
	}
	@Override
	public void setGlobalDefaultAction(BiConsumer<String, WorldContext> globalDefaultAction) {
		this.globalDefaultAction = globalDefaultAction;
	}

	@Override
	public Image getDisplayedImage() {
		return displayedImage;
	}
	@Override
	public void setDisplayedImage(Image displayedImage) {
		this.displayedImage = displayedImage;
	}

	@Override
	public String getDisplayedDialogue() {
		return displayedDialogue;
	}
	@Override
	public void setDisplayedDialogue(String displayedDialogue) {
		this.displayedDialogue = displayedDialogue;
	}

	@Override
	public String getInputText() {
		return inputText;
	}
	@Override
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
}
