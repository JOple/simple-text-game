package backup;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;
import com.droom.world.WorldState;

public class SceneQ4E extends WorldScene {

	public SceneQ4E() {
		super(R.Img.QUADRANT_4_EAST);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ4N.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ4W.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ4S.class);
	}

	@Override
	public void onCmdMoveLeft(SceneCommand cmd) {
		say("Sidestep left");
		goTo(SceneQ1E.class);
	}
	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ3E.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Window with rails");
	}
	@Override
	public void onCmdInspectWindow(SceneCommand cmd) {
		if(WorldState.hasSaw)
			say("I could Use the Saw on the Window");
		else
			say("I could break the Window if I have a Saw");
	}

	@Override
	public void onCmdUse(SceneCommand cmd) {
		say("Use what? Blade, Handle, Saw?");
	}
	@Override
	public void onCmdUseBlade(SceneCommand cmd) {
		if(WorldState.hasSawBlade)
			say("I can't use the Blade only", "I need a Handle for the Blade");
		else
			say("I don't have any Blade");
		abort();
	}
	@Override
	public void onCmdUseHandle(SceneCommand cmd) {
		if(WorldState.hasSawHandle)
			say("I can't use the Handle only", "I need a Blade for the Handle");
		else
			say("I don't have any Handle");
		abort();
	}
	@Override
	public void onCmdUseSaw(SceneCommand cmd) {
		if(WorldState.hasSaw) {
			say("You opened the Window", "You got out");
			goTo(SceneEndGood.class);
		} else
			say("I don't have a Saw");
	}
}
