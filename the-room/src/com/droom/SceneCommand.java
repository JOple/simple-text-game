package com.droom;

public interface SceneCommand {

	String getRawCommand();
	String getAction();
	String[] getArguments();
	
	default String getArgument() {
		if(getArguments() == null)
			return null;
		return String.join(" ", getArguments());
	}
}
