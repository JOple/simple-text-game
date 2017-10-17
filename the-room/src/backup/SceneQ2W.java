package backup;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ2W extends WorldScene {

	public SceneQ2W() {
		super(R.Img.QUADRANT_2_WEST);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ2S.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ2E.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ2N.class);
	}

	@Override
	public void onCmdMoveLeft(SceneCommand cmd) {
		say("Sidestep left");
		goTo(SceneQ3W.class);
	}
	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ1W.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a door that I can Open");
	}
	@Override
	public void onCmdInspectDoor(SceneCommand cmd) {
		say("I could open the Door but I think it's dangerous");
	}
	
	@Override
	public void onCmdOpenDoor(SceneCommand cmd) {
		say("You asked for it");
		goTo(SceneEndBad.class);
	}
}
