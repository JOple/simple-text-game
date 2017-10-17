package com.droom.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.droom.SceneCommand;

public class Utils {

	@SafeVarargs
	public static <T> T pickRand(T... vals) {
		return vals[(int) (Math.random() * vals.length)];
	}
	
	public static Image loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException("An error occured on loading the image", e);
		}
	}
	
	public static <T> String arrayToString(T[] arr) {
		LinkedList<T> list = new LinkedList<>();
		for(T e : arr)
			list.add(e);
		return list.toString();
	}
	
	public static SceneCommand cmd(String raw, String act, String... args) {
		final String str = String.format("SceneCommand [raw=%s, act=%s, args=%s", raw, act, arrayToString(args));
		return new SceneCommand() {

			@Override public String getAction() { return act; }
			@Override public String[] getArguments() { return args; }
			@Override public String getRawCommand() { return raw; }
			@Override public String toString() { return str; }
		};
	}
	public static SceneCommand cmdArgs(String act, String... args) {
		return cmd(null, act, args);
	}
	public static SceneCommand cmdAct(String act) {
		return cmdArgs(act, new String[0]);
	}
}
