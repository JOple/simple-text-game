package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ1E extends WorldScene {

	public SceneQ1E() {
		super(R.Img.QUADRANT_1_EAST);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ1N.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ1W.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ1S.class);
	}

	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ2E.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ4E.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a bed");
	}
	@Override
	public void onCmdInspectFront(SceneCommand cmd) {
		onCmdInspect(cmd);
	}
	@Override
	public void onCmdInspectBed(SceneCommand cmd) {
		say("There is a bed that I cannot take");
	}
}
