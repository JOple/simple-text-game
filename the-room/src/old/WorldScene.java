package old;

import java.awt.Image;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public interface WorldScene  extends Consumer<CommandInfo> {

	@Override
	default void accept(CommandInfo cs) {
		String targetName = ("onCmd" + cs.getAction()).toLowerCase();
		Method[] methods = getClass().getDeclaredMethods();
		int i, count = methods.length;
		
		for(i = 0; i < count; i++) {
			Method method = methods[i];

			if(!method.getName().toLowerCase().equals(targetName))
				continue;

			Class<?>[] params = method.getParameterTypes();

			int len = params.length;

			try {
				if(len == 0) {
					method.invoke(this);
					break;
				} else if(len == 1) {
					if(CommandInfo.class.isAssignableFrom(params[0])) {
						method.invoke(this, cs);
						break;
					} else if(CharSequence.class.isAssignableFrom(params[0])) {
						method.invoke(this, cs.getArgument());
						break;
					}
				} else if(len < 4) {
					boolean allowed = true;
					for(Class<?> param : params) {
						if(!CharSequence.class.isAssignableFrom(param)) {
							allowed = false;
							break;
						}
					}

					if(!allowed)
						continue;

					if(len == 2) {
						method.invoke(this, cs.getArgument(), cs.getCommand());
						break;
					} else if(len == 3) {
						method.invoke(this, cs.getArgument(), cs.getCommand(), cs.getEntireInput());
						break;
					}
				}
			} catch(Exception e) {
				throw new RuntimeException("Error invoking method", e);
			}
		}
		
		if(i == count)
			defaultAction(cs);
	}
	
	default void initialization() { }
	default void finalization() { }

	default void defaultAction(CommandInfo cs) { }

	default void abort() {
		throw new WorldSceneAbortException();
	}
	default void abort(Runnable postAction) {
		throw new WorldSceneAbortException(postAction);
	}
	
	default void skip() {
		throw new WorldSceneSkipException();
	}
	default void skip(Runnable postAction) {
		throw new WorldSceneSkipException(postAction);
	}
	
	default void log(String msg) {
		World.log(msg);
	}
	default void pause() {
		World.pause();
	}
	default void display(Image img) {
		World.display(img);
	}
	default void say(String... msgs) {
		World.say(msgs);
	}
	default void ask(String msg, Consumer<String> handler) {
		World.ask(msg, handler);
	}
	default <T extends Consumer<CommandInfo>> void goTo(Class<T> clz) {
		World.goTo(clz);
	}
}
