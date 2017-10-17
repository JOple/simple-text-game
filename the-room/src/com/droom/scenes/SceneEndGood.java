package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneEndGood extends WorldScene {

	private int cycle;

	public SceneEndGood() {
		super(R.Img.END_GOOD_1);
	}

	@Override
	public void initialization() {
		super.initialization();
		cycle = 0;
	}

	private void next() {
		cycle++;
		switch(cycle) {
		case 1:
			show(R.Img.END_GOOD_2);
			say("And all is well");
			break;
		default:
			say("And so we start again");
			goTo(SceneStart.class);
		}
	}

	@Override
	public void perform(SceneCommand cmd) {
		next();
	}
}
