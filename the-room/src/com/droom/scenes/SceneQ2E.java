package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ2E extends WorldScene {

	public SceneQ2E() {
		super(R.Img.QUADRANT_2_EAST);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ2N.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ2W.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ2S.class);
	}

	@Override
	public void onCmdMoveFront(SceneCommand cmd) {
		say("Moving forward");
		goTo(SceneQ1E.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ3E.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Bed", "But I need to go nearer to examine properly");
	}
}
