package old.legacy2;

import old.legacy2.scenes.*;

public class Driver {

	public static void main(String[] args) {
		WorldUI ui = new WorldUI();

		ui.setGlobalDefaultAction((cmd, ctx) -> {
			cmd = cmd == null ? "" : cmd;
			
			if(cmd.length() == 0) {
				ctx.setDisplayedDialogue(Utils.pickRandomly(
						"Cannot do no action",
						"Please type something",
						"Need to do anything"
						));
				return;
			}
			
			cmd = cmd.length() > 10 ? cmd.substring(0, 20) + " ..." : cmd;
			ctx.setDisplayedDialogue(Utils.pickRandomly(
					String.format("I don't think I can do \"%s\". Think another thing", cmd),
					String.format("\"%s\" What?", cmd),
					"I'm not feeling lucky. What will I do?",
					"I don't understand. Please try again."
					));
		});
		
		ui.addScene(new SceneStart());
		ui.addScene(new SceneQ1S());
		ui.setCurrentScene("S");
	}

}
