package backup;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ3N extends WorldScene {

	public SceneQ3N() {
		super(R.Img.QUADRANT_3_NORTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ3W.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ3S.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ3E.class);
	}

	@Override
	public void onCmdMoveFront(SceneCommand cmd) {
		say("Moving forward");
		goTo(SceneQ2N.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ4N.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Clock", "But I need to go nearer to examine properly");
	}
}
