package com.droom.world;

import java.awt.Image;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import com.droom.Scene;
import com.droom.SceneCommand;

public class World {

	public interface CommandActuator {

		void say(String ...msgs);
		void ask(String msg, Consumer<String> handler);
		void show(Image img);
		default void input(String in) {
			World.perform(in);
		}
	}
	public interface CommandParser {

		List<SceneCommand> parseCommand(String cmd);
	}

	private static CommandActuator commandActuator;
	private static CommandParser commandParser;
	private static Scene currentScene;
	private static Map<String, Scene> scenes = new Hashtable<>();

	private World() { }

	public static Map<String, Scene> getScenes() {
		return scenes;
	}
	public static Scene getScene(String name) {
		return scenes.get(name);
	}
	public static void addScene(String name, Scene scene) {
		scenes.put(name, scene);
	}
	public static void removeScene(String name) {
		scenes.remove(name);
	}

	public static Set<String> getSceneNames() {
		return scenes.keySet();
	}

	public static Scene getCurrentScene() {
		return currentScene;
	}
	public static void setCurrentScene(String name) {
		World.currentScene = scenes.get(name);
	}

	public static CommandActuator getCommandActuator() {
		return commandActuator;
	}
	public static void setCommandActuator(CommandActuator commandActuator) {
		World.commandActuator = commandActuator;
	}

	public static CommandParser getCommandParser() {
		return commandParser;
	}
	public static void setCommandParser(CommandParser commandParser) {
		World.commandParser = commandParser;
	}

	public static void perform(String input) {
		Objects.requireNonNull(getCommandParser(), "Parser is not set");
		loop:
		for(SceneCommand cmd : getCommandParser().parseCommand(input)) {
			try {
				getCurrentScene().perform(cmd);
			} catch(WorldScene.FlowControlException e) {
				switch(e.getFlowControlType()) {
				case WorldMacros.FLOW_CONTROL_ABORT:
					e.run();
					break loop;
				case WorldMacros.FLOW_CONTROL_SKIP:
					e.run();
					continue loop;
				}
				throw new IllegalStateException("Unregistered Flow Control Type");
			}
		}
	}

	public static void say(String... msgs) {
		Objects.requireNonNull(getCommandActuator(), "Actuator is not set");
		commandActuator.say(msgs);
	}
	public static void ask(String msg, Consumer<String> handler) {
		Objects.requireNonNull(getCommandActuator(), "Actuator is not set");
		commandActuator.ask(msg, handler);
	}
	public static void show(Image img) {
		Objects.requireNonNull(getCommandActuator(), "Actuator is not set");
		commandActuator.show(img);
	}
	
	public static void goTo(String name) {
		if(getCurrentScene() != null)
			getCurrentScene().finalization();
		setCurrentScene(name);
		if(getCurrentScene() != null)
			getCurrentScene().initialization();
	}
	public static <T extends Scene> void goTo(Class<T> clz) {
		Objects.requireNonNull(clz, "Parameter cannot be null");
		String name = clz.getSimpleName();
		if(World.getScene(name) == null) {
			try {
				addScene(name, clz.newInstance());
			} catch (Exception e) {
				throw new RuntimeException("Empty constructor may be not available or private", e);
			}
		}
		goTo(name);
	}
}
