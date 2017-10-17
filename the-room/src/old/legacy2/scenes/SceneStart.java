package old.legacy2.scenes;

import old.legacy2.Utils;
import old.legacy2.WorldContext;
import old.legacy2.WorldScene;

public class SceneStart extends WorldScene {

	public SceneStart() {
		super("S");
	}
	@Override
	public void onSceneEntry(WorldContext context) {
		context.setDisplayedDialogue("One day you find yourself sleeping on a strange bed.");
		waitForSkip();

		context.setDisplayedImage(Utils.Img.START);
		context.setDisplayedDialogue("You were awoken by an unfamiliar sound.");
		waitForSkip();

		context.setDisplayedDialogue("Player: Where am I? What is this place? I must get out of here.");
		waitForSkip();

		context.setDisplayedDialogue("And so you stand from your bed.");
		waitForSkip();
		
		context.setCurrentScene("Q1S");
	}

}
