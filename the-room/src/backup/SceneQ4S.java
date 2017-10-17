package backup;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneQ4S extends WorldScene {


	private int cycle = 0;
	private String[] inspectReplies = new String[] {"The Shelf has 3 books", "The books are about trees", "Remember 3 and trees"};
	
	public SceneQ4S() {
		super(R.Img.QUADRANT_4_SOUTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ4E.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ4N.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ4W.class);
	}

	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ1S.class);
	}
	@Override
	public void onCmdMoveRight(SceneCommand cmd) {
		say("Sidestep right");
		goTo(SceneQ3S.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There are Paintings");
	}
	@Override
	public void onCmdInspectShelf(SceneCommand cmd) {
		say(inspectReplies[cycle++ % inspectReplies.length]);
	}
}
