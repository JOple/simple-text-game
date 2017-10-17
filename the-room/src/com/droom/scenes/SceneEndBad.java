package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneEndBad extends WorldScene {

	private int cycle;

	public SceneEndBad() {
		super(R.Img.END_BAD_1);
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
			show(R.Img.END_BAD_2);
			say("And there was light");
			break;
		case 2:
			show(R.Img.END_BAD_2);
			say("And destruction and you DIED");
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
