package old.legacy2.scenes;

import old.legacy2.Utils;
import old.legacy2.WorldContext;
import old.legacy2.WorldScene;

public class SceneQ1S extends WorldScene {

	public SceneQ1S() {
		super("Q1S");
		addAction(" *(open|check|inspect|(look( at)?)) +(drawer|cabinet|chest) +", (c, ctx) -> {
		});
	}
	@Override
	public void onSceneEntry(WorldContext context) {
		context.setDisplayedImage(Utils.Img.QUADRANT_1_SOUTH);
		context.setDisplayedDialogue(Utils.pickRandomly(
				"What waill you do?",
				"What now?",
				"Do an action."
				));
	}
}
