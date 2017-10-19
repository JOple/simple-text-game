package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ3W extends WorldScene {

	private int cycle = 0;
	private String[] inspectReplies = new String[] { "There are 3 heads", "There is 1 human head",
			"There are 2 dragon heads", "Remember 1 and 2" };

	public SceneQ3W() {
		super(R.Img.QUADRANT_3_WEST);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ3S.class);
	}

	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ3E.class);
	}

	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ3N.class);
	}

	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ4W.class);
	}

	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ2W.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There are Paintings");
	}

	@Override
	public void onCmdInspectPainting(SceneCommand cmd) {
		say(inspectReplies[cycle++ % inspectReplies.length]);
	}
}
