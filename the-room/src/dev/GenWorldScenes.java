package dev;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class GenWorldScenes {

	private static final Map<String, String> directionName = new Hashtable<>();
	static {
		directionName.put("N", "NORTH");
		directionName.put("S", "SOUTH");
		directionName.put("E", "EAST");
		directionName.put("W", "WEST");
	}

	private static final String template =
			"\t@Override\r\n" +
					"\tpublic void onCmd%s%s(SceneCommand cmd) {\r\n" +
					"\t\tsuper.onCmd%s%s(cmd);\r\n" +
					"\t\tgoTo(SceneQ%s%s.class);\r\n" +
					"\t}\r\n";
	private static String form(Object act, Object arg, Object q, Object n) {
		return String.format(template, act, arg, act, arg, q, n);
	}

	private static final String template2 =
			"\t@Override\r\n" +
					"\tpublic void onCmd%s%s(SceneCommand cmd) {\r\n" +
					"\t\tsay(\"%s\");\r\n" +
					"\t\tgoTo(SceneQ%s%s.class);\r\n" +
					"\t}\r\n";
	private static String form2(Object act, Object arg, Object say, Object q, Object n) {
		return String.format(template2, act, arg, say, q, n);
	}

	public static void main(String[] args) throws IOException {

		for(String direction : new String[] {"N", "S", "E", "W"}) {
			for(int quadrant = 1; quadrant <= 4; quadrant++) {
				String name = "SceneQ" + quadrant + direction;

				StringBuffer buf = new StringBuffer();
				buf.append("package com.droom.scenes;\r\n\r\n");
				buf.append("import com.droom.SceneCommand;\r\n");
				buf.append("import com.droom.utils.R;\r\n");
				buf.append("import com.droom.world.WorldScene;\r\n\r\n");
				buf.append("public class " + name + " extends WorldScene {\r\n\r\n");
				buf.append("\tpublic " + name +"() {\r\n");
				buf.append("\t\tsuper(R.Img.QUADRANT_" + quadrant + "_" + directionName.get(direction) + ");\r\n");
				buf.append("\t}\r\n\r\n");

				buf.append(form( "Look", "Left", quadrant, leftOf(direction)));
				buf.append(form( "Look", "Back", quadrant, backOf(direction)));
				buf.append(form( "Look", "Right", quadrant, rightOf(direction)));

				buf.append("\r\n");

				Integer n = frontOf(quadrant, direction);
				if(n != null)
					buf.append(form2( "Move", "Front", "Moving forward", n, direction));
				n = leftOf(quadrant, direction);
				if(n != null)
					buf.append(form2( "Move", "Left", "Sidestep left", n, direction));
				n = backOf(quadrant, direction);
				if(n != null)
					buf.append(form2( "Move", "Back", "Back walking", n, direction));
				n = rightOf(quadrant, direction);
				if(n != null)
					buf.append(form2( "Move", "Right", "Sidestep right", n, direction));

				buf.append("}\r\n");
				
				FileWriter w = new FileWriter(new File("src/com/droom/scenes/" + name +".java"));
				w.write(buf.toString());
				w.close();
			}
		}
	}

	public static String leftOf(String s) {
		switch(s) {
		case "N": return "W";
		case "S": return "E";
		case "E": return "N";
		case "W": return "S";
		}
		throw new IllegalArgumentException();
	}
	public static String rightOf(String s) {
		switch(s) {
		case "N": return "E";
		case "S": return "W";
		case "E": return "S";
		case "W": return "N";
		}
		throw new IllegalArgumentException();
	}
	public static String backOf(String s) {
		switch(s) {
		case "N": return "S";
		case "S": return "N";
		case "E": return "W";
		case "W": return "E";
		}
		throw new IllegalArgumentException();
	}

	public static Integer frontOf(int q, String dir) {
		switch(q + dir) {
		case "1S": return 4;
		case "2E": return 1;
		case "3N": return 2;
		case "4W": return 3;
		
		case "1W": return 2;
		case "2S": return 3;
		case "3E": return 4;
		case "4N": return 1;
		}
		return null;
	}
	public static Integer leftOf(int q, String dir) {
		switch(q + dir) {
		case "1N": return 2;
		case "2W": return 3;
		case "3S": return 4;
		case "4E": return 1;

		case "1W": return 4;
		case "2S": return 1;
		case "3E": return 2;
		case "4N": return 3;
		}
		return null;
	}
	public static Integer backOf(int q, String dir) {
		switch(q + dir) {
		case "1N": return 4;
		case "2W": return 1;
		case "3S": return 2;
		case "4E": return 3;

		case "1E": return 2;
		case "2N": return 3;
		case "3W": return 4;
		case "4S": return 1;
		}
		return null;
	}
	public static Integer rightOf(int q, String dir) {
		switch(q + dir) {
		case "1E": return 4;
		case "2N": return 1;
		case "3W": return 2;
		case "4S": return 3;

		case "1S": return 2;
		case "2E": return 3;
		case "3N": return 4;
		case "4W": return 1;
		}
		return null;
	}
}
