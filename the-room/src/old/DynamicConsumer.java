package old;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class DynamicConsumer<T>  implements Consumer<T> {

	private Consumer<T> current;
	private Map<String, Consumer<T>> consumers;

	public DynamicConsumer() {
		consumers = new Hashtable<>();
	}

	public Consumer<T> get(String key) {
		return consumers.get(key);
	}
	public void put(String key, Consumer<T> c) {
		consumers.put(key, c);
	}
	public void remove(String key) {
		consumers.remove(key);
	}

	public Set<String> keySet() {
		return consumers.keySet();
	}

	public Consumer<T> current() {
		return current;
	}
	public void current(String key) {
		current = consumers.get(key);
	}

	@Override
	public void accept(T t) {
		Objects.requireNonNull(current, "Current consumer is null");
		current.accept(t);
	}
}
