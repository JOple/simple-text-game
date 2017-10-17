package com.droom.world;

import java.awt.Image;
import java.util.function.Consumer;

import com.droom.Scene;
import com.droom.world.WorldScene.FlowControlException;

public interface WorldMacros {

	public static final String FLOW_CONTROL_ABORT = "abort";
	public static final String FLOW_CONTROL_SKIP = "skip";
	
	default void say(String... msgs) {
		World.say(msgs);
	}
	default void ask(String msg, Consumer<String> handler) {
		World.ask(msg, handler);
	}
	default void show(Image img) {
		World.show(img);
	}
	default void goTo(String name) {
		World.goTo(name);
	}
	default <T extends Scene> void goTo(Class<T> clz) {
		World.goTo(clz);
	}

	default void abort() {
		throw new FlowControlException(FLOW_CONTROL_ABORT);
	}
	default void abort(Runnable postAction) {
		throw new FlowControlException(FLOW_CONTROL_ABORT, postAction);
	}

	default void skip() {
		throw new FlowControlException(FLOW_CONTROL_SKIP);
	}
	default void skip(Runnable postAction) {
		throw new FlowControlException(FLOW_CONTROL_SKIP, postAction);
	}
}
