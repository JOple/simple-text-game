package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ2N extends WorldScene {

	private int cycle = 0;
	private String[] inspectReplies = new String[] {"The Clock has a time of 2:20", "The hour hand points at 2", "The minute hand points at 4", "Remember 2 and 4"};
	
	public SceneQ2N() {
		super(R.Img.QUADRANT_2_NORTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ2W.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ2S.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ2E.class);
	}

	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ3N.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ1N.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Clock");
	}
	@Override
	public void onCmdInspectClock(SceneCommand cmd) {
		say(inspectReplies[cycle++ % inspectReplies.length]);
	}
}
