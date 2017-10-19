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
			say("You opened your eyes in an unknown room");
			break;
		case 2:
			say("You need to get out");
			break;
		case 3:
			say("You get off the bed");
			break;
		default:
			say("What will I do");
			goTo(SceneQ1S.class);
		}
	}

	@Override
	public void perform(SceneCommand cmd) {
		next();
	}
}
