package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ4N extends WorldScene {

	public SceneQ4N() {
		super(R.Img.QUADRANT_4_NORTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ4W.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ4S.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ4E.class);
	}

	@Override
	public void onCmdMoveFront(SceneCommand cmd) {
		say("Moving forward");
		goTo(SceneQ1N.class);
	}
	@Override
	public void onCmdMoveLeft(SceneCommand cmd) {
		say("Sidestep left");
		goTo(SceneQ3N.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Drawer", "But I need to go nearer to examine properly");
	}
}
