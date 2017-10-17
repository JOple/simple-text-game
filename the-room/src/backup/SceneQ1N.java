package backup;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;
import com.droom.world.WorldState;

public class SceneQ1N extends WorldScene {

	private boolean isInspected = false;
	private boolean hasSawBlade = true;
	
	public SceneQ1N() {
		super(R.Img.QUADRANT_1_NORTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ1W.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ1S.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ1E.class);
	}

	@Override
	public void onCmdMoveLeft(SceneCommand cmd) {
		say("Sidestep left");
		goTo(SceneQ2N.class);
	}
	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ4N.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Drawer");
	}
	@Override
	public void onCmdInspectFront(SceneCommand cmd) {
		onCmdInspect(cmd);
	}
	@Override
	public void onCmdInspectDrawer(SceneCommand cmd) {
		say("There is a Drawer that I cannot take", "And inside is a Blade");
		isInspected = true;
	}
	
	@Override
	public void onCmdTake(SceneCommand cmd) {
		if(isInspected) {
			if(hasSawBlade) {
				say("I took the Blade");
				hasSawBlade = false;
				WorldState.hasSawBlade = true;
				if(WorldState.hasSawHandle)
					say("I can now combine the Blade and the Handle");
			} else
				say("I already took the Blade");
		} else {
			say("I don't even know what to take", "Inspect first");
			abort();
		}
	}
	@Override
	public void onCmdTakeBlade(SceneCommand cmd) {
		if(isInspected) {
			if(hasSawBlade) {
				say("I took the Blade");
				hasSawBlade = false;
				WorldState.hasSawBlade = true;
				if(WorldState.hasSawHandle)
					say("I can now combine the Blade and the Handle");
			} else
				say("I already took the Blade");
		} else {
			say("I'm not even sure if there is a Blade here", "Inspect first");
			abort();
		}
	}
}
