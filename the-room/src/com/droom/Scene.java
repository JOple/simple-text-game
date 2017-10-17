package com.droom;

public interface Scene {

	void initialization();
	void finalization();
	void perform(SceneCommand cmd);
}
