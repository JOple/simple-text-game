package backup;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ4W extends WorldScene {

	public SceneQ4W() {
		super(R.Img.QUADRANT_4_WEST);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ4S.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ4E.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ4N.class);
	}

	@Override
	public void onCmdMoveFront(SceneCommand cmd) {
		say("Moving forward");
		goTo(SceneQ3W.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ1W.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Painting", "But I need to go nearer to examine properly");
	}
}
