package dev;

public class GenDefaultWorldActions {
	public static final String template = "default void onCmd%s%s(SceneCommand cmd) {\n\tsay(\"%s\");\n}";
	public static void main(String[] args) {
		for(String dir : new String[] {"", "Unknown"}) {
			p("Default", dir, "I don't understand");
		}
		for(String dir : new String[] {"", "Unknown", "Front", "Left", "Back", "Right", "Up", "Down"}) {
			p("Look", dir, "Looking " + dir);
		}
		for(String dir : new String[] {"", "Unknown", "Front", "Left", "Back", "Right", "Up", "Down"}) {
			p("Move", dir, "Moving " + dir);
		}
		for(String dir : new String[] {"", "Unknown", "Front", "Left", "Back", "Right", "Up", "Down", "Bed", "Lamp", "Clock", "Door", "Painting", "Chest", "Shelf", "Window", "Blade", "Handle", "Saw"}) {
			p("Inspect", dir, "I think the " + dir + " is somewhere in the room");
		}
		for(String dir : new String[] {"", "Unknown", "Bed", "Lamp", "Clock", "Door", "Painting", "Chest", "Shelf", "Window", "Blade", "Handle", "Saw"}) {
			p("Take", dir, "The " + dir + " is somewhere in the room");
		}
		for(String dir : new String[] {"", "Unknown"}) {
			p("Inventory", dir, "I dont have anything");
		}
		for(String dir : new String[] {"", "Unknown"}) {
			p("Input", dir, "I need a chest");
		}
		for(String dir : new String[] {"", "Unknown"}) {
			p("Combine", dir, "I need a handle and blade");
		}
		for(String dir : new String[] {"", "Unknown", "Blade", "Handle", "Saw"}) {
			p("Use", dir, "I only know how to use a saw");
		}
		for(String dir : new String[] {"", "Unknown", "Door", "Chest" ,"Window"}) {
			p("Open", dir, "I can't open that");
		}

	}
	public static void p(String act, String arg, String say) {
		System.out.println(String.format(template, act, arg, say));
	}
}
