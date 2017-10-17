package old.legacy.scenes;

public class SceneQ1N extends WorldScene {

	public SceneQ1N() {
		super(null);
		defaultAction((c, w) -> {
			System.out.println(c);
		});
	}

	@Override
	public void onEntry() {
		super.onEntry();
	}
}
