package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ1S extends WorldScene {

	public SceneQ1S() {
		super(R.Img.QUADRANT_1_SOUTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ1E.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ1N.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ1W.class);
	}

	@Override
	public void onCmdMoveFront(SceneCommand cmd) {
		say("Moving forward");
		goTo(SceneQ4S.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ2S.class);
	}
	
	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Bookshelf", "But I need to go nearer to examine properly");
	}
}
