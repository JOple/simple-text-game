package old.legacy2;

import java.util.function.BiConsumer;

public interface SceneAction<C> extends BiConsumer<String, C> {

	boolean check(String command, C context);
}
