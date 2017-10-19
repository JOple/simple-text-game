package com.droom.world;

import com.droom.SceneCommand;
import com.droom.utils.Utils;

public interface DefaultWorldActions extends WorldMacros {

	default void onCmdDefault(SceneCommand cmd) {
		int i = WorldState.idle++ % 20;
		if (i > 9 && i < 17) {
			say(Utils.pickRand("You don't go out much do you", "I wonder how I got here",
					"Why don't I just open the door", "To win the game delete System32"));
		}
		abort();
	}

	default void onCmdDefaultUnknown(SceneCommand cmd) {
		say("I don't understand what '" + cmd.getArgument() + "' is");
		abort();
	}

	default void onCmdLook(SceneCommand cmd) {
		say("Where do I look? Front, left, back, right?");
		abort();
	}

	default void onCmdLookUnknown(SceneCommand cmd) {
		say("I don't know what '" + cmd.getArgument() + "' is");
		abort();
	}

	default void onCmdLookFront(SceneCommand cmd) {
		say("I'm alread looking Frontwards");
		abort();
	}

	default void onCmdLookLeft(SceneCommand cmd) {
		say("Looking Left");
	}

	default void onCmdLookBack(SceneCommand cmd) {
		say("Looking Back");
	}

	default void onCmdLookRight(SceneCommand cmd) {
		say("Looking Right");
	}

	default void onCmdLookUp(SceneCommand cmd) {
		say("Sorry, I can't look up");
		abort();
	}

	default void onCmdLookDown(SceneCommand cmd) {
		say("I can't look down");
		abort();
	}

	default void onCmdMove(SceneCommand cmd) {
		say("Move where? Front, left, back, right?");
		abort();
	}

	default void onCmdMoveUnknown(SceneCommand cmd) {
		say("I don't know how to move to " + cmd.getArgument());
		abort();
	}

	default void onCmdMoveFront(SceneCommand cmd) {
		say("I can't move forward");
		abort();
	}

	default void onCmdMoveLeft(SceneCommand cmd) {
		say("I can't move leftward");
		abort();
	}

	default void onCmdMoveBack(SceneCommand cmd) {
		say("I can't move backward");
		abort();
	}

	default void onCmdMoveRight(SceneCommand cmd) {
		say("I can't move rightward");
		abort();
	}

	default void onCmdMoveUp(SceneCommand cmd) {
		say("I believe I can't fly");
		abort();
	}

	default void onCmdMoveDown(SceneCommand cmd) {
		say("I can't burrow");
		abort();
	}

	default void onCmdInspect(SceneCommand cmd) {
		say("Inspect what? Clock, Bed?");
		abort();
	}

	default void onCmdInspectUnknown(SceneCommand cmd) {
		say("I don't know about " + cmd.getArgument());
		abort();
	}

	default void onCmdInspectFront(SceneCommand cmd) {
		onCmdInspect(cmd);
	}

	default void onCmdInspectLeft(SceneCommand cmd) {
		say("I'm going to look Left first");
		onCmdLookLeft(cmd);
	}

	default void onCmdInspectBack(SceneCommand cmd) {
		say("I'm going to look Back first");
		onCmdLookBack(cmd);
	}

	default void onCmdInspectRight(SceneCommand cmd) {
		say("I'm going to look Right first");
		onCmdLookRight(cmd);
	}

	default void onCmdInspectUp(SceneCommand cmd) {
		say("I have a roof on my head");
	}

	default void onCmdInspectDown(SceneCommand cmd) {
		say("The floor is sturdy");
	}

	default void onCmdInspectBed(SceneCommand cmd) {
		say("I think the Bed is somewhere in the room");
		abort();
	}

	default void onCmdInspectDrawer(SceneCommand cmd) {
		say("I think the Drawer is somewhere in the room");
		abort();
	}

	default void onCmdInspectClock(SceneCommand cmd) {
		say("I think the Clock is somewhere in the room");
		abort();
	}

	default void onCmdInspectDoor(SceneCommand cmd) {
		say("I think the Door is somewhere in the room");
		abort();
	}

	default void onCmdInspectPainting(SceneCommand cmd) {
		say("I think the Painting is somewhere in the room");
		abort();
	}

	default void onCmdInspectChest(SceneCommand cmd) {
		say("I think the Chest is somewhere in the room");
		abort();
	}

	default void onCmdInspectShelf(SceneCommand cmd) {
		say("I think the Shelf is somewhere in the room");
		abort();
	}

	default void onCmdInspectWindow(SceneCommand cmd) {
		say("I think the Window is somewhere in the room");
		abort();
	}

	default void onCmdInspectBlade(SceneCommand cmd) {
		if (WorldState.hasSawBlade && WorldState.hasSawHandle)
			say("I could make a saw using this blade and the handle");
		if (WorldState.hasSawBlade && !WorldState.hasSawHandle)
			say("The blade is sharp");
		if (!WorldState.hasSawBlade) {
			say("I think you can pick up the Blade is somewhere in the room");
			abort();
		}
	}

	default void onCmdInspectHandle(SceneCommand cmd) {
		if (WorldState.hasSawBlade && WorldState.hasSawHandle)
			say("I could make a saw using this handle and the blade");
		if (!WorldState.hasSawBlade && WorldState.hasSawHandle)
			say("The handle needs a blade");
		if (!WorldState.hasSawHandle) {
			say("I think you can pick up the Handle is somewhere in the room");
			abort();
		}
	}

	default void onCmdInspectSaw(SceneCommand cmd) {
		if (WorldState.hasSaw)
			say("I could use the saw on the window");
		else {
			say("I think the Saw is somewhere in the room");
			abort();
		}
	}

	default void onCmdTake(SceneCommand cmd) {
		say("The only takeable items are blade and handle I think");
		abort();
	}

	default void onCmdTakeUnknown(SceneCommand cmd) {
		say("I can't take what I don't know");
		abort();
	}

	default void onCmdTakeBed(SceneCommand cmd) {
		say("I can't take the Bed");
		abort();
	}

	default void onCmdTakeDrawer(SceneCommand cmd) {
		say("I can't take the Drawer");
		abort();
	}

	default void onCmdTakeClock(SceneCommand cmd) {
		say("I can't take the Clock");
		abort();
	}

	default void onCmdTakeDoor(SceneCommand cmd) {
		say("I can't take the Door");
		abort();
	}

	default void onCmdTakePainting(SceneCommand cmd) {
		say("I can't take the Painting");
		abort();
	}

	default void onCmdTakeChest(SceneCommand cmd) {
		say("I can't take the Chest");
		abort();
	}

	default void onCmdTakeShelf(SceneCommand cmd) {
		say("I can't take the Shelf");
		abort();
	}

	default void onCmdTakeWindow(SceneCommand cmd) {
		say("I can't take the Window");
		abort();
	}

	default void onCmdTakeBlade(SceneCommand cmd) {
		if (WorldState.hasSawBlade)
			say("I already have the Blade");
		else
			say("The Blade is somewhere in the room");
		abort();
	}

	default void onCmdTakeHandle(SceneCommand cmd) {
		if (WorldState.hasSawHandle)
			say("I already have the Handle");
		else
			say("The Handle is somewhere in the room");
		abort();
	}

	default void onCmdTakeSaw(SceneCommand cmd) {
		if (WorldState.hasSaw)
			say("I already have the saw");
		else
			say("I can't take the Saw, I can only make it");
		abort();
	}

	default void onCmdInventory(SceneCommand cmd) {
		if (WorldState.hasSaw)
			say("I have a saw that can be used on the window");
		else if (WorldState.hasSawBlade && WorldState.hasSawHandle)
			say("I have a Blade and Handle that I can combine to make a Saw");
		else if (WorldState.hasSawBlade)
			say("I have a Blade that needs a Handle");
		else if (WorldState.hasSawHandle)
			say("I have a Handle that needs a Blade");
		else
			say("I dont have anything");
	}

	default void onCmdInventoryUnknown(SceneCommand cmd) {
		say("I don't know what '" + cmd.getArgument() + "' is");
		say("But...");
		onCmdInventory(cmd);
	}

	default void onCmdInput(SceneCommand cmd) {
		say("I need a chest");
		abort();
	}

	default void onCmdInputUnknown(SceneCommand cmd) {
		say("I need a chest");
		abort();
	}

	default void onCmdCombine(SceneCommand cmd) {
		if (WorldState.hasSaw)
			say("I alread combine what I can and has a Saw");
		else if (WorldState.hasSawBlade && WorldState.hasSawHandle) {
			say("Combining blade and handle now I have a Saw");
			WorldState.hasSaw = true;
			WorldState.hasSawBlade = false;
			WorldState.hasSawHandle = false;
		} else if (WorldState.hasSawBlade) {
			say("I only have a Blade I need a Handle");
			abort();
		} else if (WorldState.hasSawHandle) {
			say("I only have a Handle I need a Blade");
			abort();
		} else {
			say("I dont have anything to combine");
			abort();
		}
	}

	default void onCmdCombineUnknown(SceneCommand cmd) {
		say("I don't know what '" + cmd.getArgument() + "' is");
		say("But...");
		onCmdCombine(cmd);
	}

	default void onCmdUse(SceneCommand cmd) {
		say("I only know how to use a saw");
		abort();
	}

	default void onCmdUseUnknown(SceneCommand cmd) {
		say("I only know how to use a saw");
		abort();
	}

	default void onCmdUseBlade(SceneCommand cmd) {
		say("I can't use a blade only");
		abort();
	}

	default void onCmdUseHandle(SceneCommand cmd) {
		say("I can't use the handle only");
		abort();
	}

	default void onCmdUseSaw(SceneCommand cmd) {
		say("I need to be near the window to use the saw");
		abort();
	}

	default void onCmdOpen(SceneCommand cmd) {
		say("I can't open that");
		abort();
	}

	default void onCmdOpenUnknown(SceneCommand cmd) {
		say("I can't open that");
		abort();
	}

	default void onCmdOpenDoor(SceneCommand cmd) {
		say("I need to be near the door");
		abort();
	}

	default void onCmdOpenChest(SceneCommand cmd) {
		say("I need to be near the Chest");
	}

	default void onCmdOpenWindow(SceneCommand cmd) {
		say("I can't open the Window I need a Saw");
		abort();
	}
}
