package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneStart extends WorldScene {

	public SceneStart() {
		super(R.Img.START);
	}

	private int cycle;

	@Override
	public void initialization() {
		super.initialization();
		cycle = 0;
	}

	private void next() {
		cycle++;
		switch (cycle) {
		case 1:
			say("You opened you eyes in a unknown room");
			break;
		case 2:
			say("You need to get out");
			break;
		default:
			say("You get off the bed");
			goTo(SceneQ1S.class);
		}
	}

	@Override
	public void perform(SceneCommand cmd) {
		next();
	}
}
