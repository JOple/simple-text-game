package com.droom.scenes;

import com.droom.SceneCommand;
import com.droom.utils.R;
import com.droom.world.WorldScene;
import com.droom.world.WorldState;

public class SceneQ3S extends WorldScene {

	private int cycle = 0;

	private boolean isInspected = false;
	private boolean isLocked = true;
	private boolean isOpened = false;
	private boolean hasSawHandle = true;

	public SceneQ3S() {
		super(R.Img.QUADRANT_3_SOUTH);
	}

	@Override
	public void onCmdLookLeft(SceneCommand cmd) {
		super.onCmdLookLeft(cmd);
		goTo(SceneQ3E.class);
	}
	@Override
	public void onCmdLookBack(SceneCommand cmd) {
		super.onCmdLookBack(cmd);
		goTo(SceneQ3N.class);
	}
	@Override
	public void onCmdLookRight(SceneCommand cmd) {
		super.onCmdLookRight(cmd);
		goTo(SceneQ3W.class);
	}

	@Override
	public void onCmdMoveLeft(SceneCommand cmd) {
		say("Sidestep left");
		goTo(SceneQ4S.class);
	}
	@Override
	public void onCmdMoveBack(SceneCommand cmd) {
		say("Back walking");
		goTo(SceneQ2S.class);
	}

	@Override
	public void onCmdInspect(SceneCommand cmd) {
		say("There is a Chest");
	}
	@Override
	public void onCmdInspectChest(SceneCommand cmd) {
		cycle++;
		if(!isInspected) {
			say("The Chest needs me to Input a 3-digit number in order to be unlocked");
			isInspected = true;
		} else if(isLocked)
			say("It is locked and you need the 3-numbers to be Input in the Chest");
		
		else if(isOpened) {
			say("It is already opened and has a Handle inside");
			if(!hasSawHandle)
				say("But the Handle is already yours");
		}
		if(cycle > 10)
			say("You saw a little paper having the number 243");
	}
	
	@Override
	public void onCmdTake(SceneCommand cmd) {
		if(!isInspected) {
			say("I cannot take what I don't know", "Inspect first");
			abort();
		} else if(isLocked) {
			say("I cannot take what is locked behind the Chest", "Unlock it first");
			abort();
		}
		if(!isOpened) {
			say("Even though it is unlocked, it is still closed", "Open it first");
			abort();
		}
		
		if(hasSawHandle) {
			say("I got the Handle");
			hasSawHandle = false;
			WorldState.hasSawHandle = true;
			if(WorldState.hasSawBlade)
				say("I can now combine the Handle and the Blade");
		} else
			say("I already got the saw Handle");
	}
	@Override
	public void onCmdTakeHandle(SceneCommand cmd) {
		if(!isOpened) {
			say("I don't know where the Handle is");
			abort();
		}
		
		if(hasSawHandle) {
			say("I got the Handle");
			hasSawHandle = false;
			WorldState.hasSawHandle = true;
			if(WorldState.hasSawBlade)
				say("I can now combine the Handle and the Blade");
		} else
			say("I already got the saw Handle");
	}

	@Override
	public void onCmdInput(SceneCommand cmd) {
		say("The chest cannot be opened without input");
		abort();
	}
	@Override
	public void onCmdInputUnknown(SceneCommand cmd) {
		say("The Input " + cmd.getArgument() + " is wrong");
		abort();
	}
	public void onCmdInput243(SceneCommand cmd) {
		say("The Chest is unlocked");
		isLocked = false;
	}

	@Override
	public void onCmdOpen(SceneCommand cmd) {
		if(isLocked) {
			say("The Chest could be opened", "But it is locked");
			abort();
		} else {
			say("The Chest is opened", "There is a Handle inside");
			isOpened = true;
		}
	}
	@Override
	public void onCmdOpenChest(SceneCommand cmd) {
		onCmdOpen(cmd);
	}
}
