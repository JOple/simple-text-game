package old.scenes;

import old.R;
import old.WorldScene;

public class SceneStart implements WorldScene {

	@Override
	public void initialization() {
		display(R.Img.START);
		say("You open your eyes in an unfamiliar room",
				"You have no memories of what happened",
				"At least you know you have to get out",
				"And you went off the bed");
		goTo(SceneQ1S.class);
	}
	public void onCmdLol(String arg, String cmd, String entire) {
	}
}
