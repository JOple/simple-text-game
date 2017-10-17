package com.droom;

import com.droom.scenes.SceneStart;
import com.droom.ui.GameUI;
import com.droom.utils.SentenceParser;
import com.droom.world.World;

public class Driver {

	public static void main(String[] args) {
		GameUI ui = new GameUI();
		World.setCommandActuator(ui);
		World.setCommandParser(SentenceParser::parse);
		World.goTo(SceneStart.class);
	}
}
