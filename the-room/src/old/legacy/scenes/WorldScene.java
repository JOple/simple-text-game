package old.legacy.scenes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;

import old.legacy.World;

public abstract class WorldScene implements Scene {

	public class Action {

		private String regex;
		private BiConsumer<String, WorldScene> action;

		public Action(String regex, BiConsumer<String, WorldScene> action) {
			Objects.requireNonNull(regex, "Regex cannot be null");
			Objects.requireNonNull(action, "Action cannot be null");

			this.regex = regex;
			this.action = action;
		}

		public String getRegex() {
			return regex;
		}
		public void setRegex(String regex) {
			this.regex = regex;
		}

		public BiConsumer<String, WorldScene> getAction() {
			return action;
		}
		public void setAction(BiConsumer<String, WorldScene> action) {
			this.action = action;
		}
	}

	public static final List<Action> GLOBAL_ACTIONS = new ArrayList<>();

	private Image localImage;
	private List<Action> actions;
	private BiConsumer<String, WorldScene> defaultAction;

	public WorldScene(String imagePath) {
		if(imagePath != null) {
			try {
				this.localImage = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				throw new RuntimeException("An error occured while reading an Image File", e);
			}
		}
		this.actions = new ArrayList<>();

		World.instance().addScene(this);
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	public Image getLocalImage() {
		return localImage;
	}
	public World getWorld() {
		return World.instance();
	}
	public List<Action> getActions() {
		return actions;
	}
	public BiConsumer<String, WorldScene> getDefaultAction() {
		return defaultAction;
	}
	public WorldScene getCurrentScene() {
		return getWorld().getCurrentScene();
	}
	public Image getDisplayedImage() {
		return getWorld().getImage();
	}
	public String getDialogue() {
		return getWorld().getDialogue();
	}

	public WorldScene localImage(Image image) {
		this.localImage = image;
		return this;
	}
	public WorldScene defaultAction(BiConsumer<String, WorldScene> defaultAction) {
		this.defaultAction = defaultAction;
		return this;
	}
	public WorldScene record(String regex, BiConsumer<String, WorldScene> action) {
		actions.add(new Action(regex, action));
		return this;
	}
	public WorldScene perform(String command) {
		onAction(command);
		return this;
	}
	public WorldScene currentScene(String name) {
		getWorld().setCurrentScene(name);
		return this;
	}
	public WorldScene displayedImage(Image image) {
		getWorld().setImage(image);
		return this;
	}
	public WorldScene displayedDialogue(String dialogue) {
		getWorld().setDialogue(dialogue);
		return this;
	}
	
	@Override
	public void onEntry() {
		getWorld().setImage(getLocalImage());
	}
	@Override
	public void onExit() { }
	@Override
	public void onAction(String command) {
		command = command == null ? "" : command;

		Action currentAct = null;
		for(Action act : actions) {
			if(command.matches(act.regex)) {
				currentAct = act;
				break;
			}
		}

		if(currentAct == null) {
			for(Action act : GLOBAL_ACTIONS) {
				if(command.matches(act.regex)) {
					currentAct = act;
					break;
				}
			}
		}

		if(currentAct != null)
			currentAct.action.accept(command, this);
		else if(defaultAction != null)
			defaultAction.accept(command, this);
	}
}
