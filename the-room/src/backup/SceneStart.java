package backup;

import com.droom.utils.R;
import com.droom.world.WorldScene;

public class SceneStart extends WorldScene {

	public SceneStart() {
		super(R.Img.START);
	}

	@Override
	public void initialization() {
		super.initialization();
		say("You opened you eyes in a unknown room", "You need to get out", "You get off the bed");
		goTo(SceneQ1S.class);
	}
}
