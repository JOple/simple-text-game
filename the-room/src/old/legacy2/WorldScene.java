package old.legacy2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class WorldScene implements Scene<WorldContext> {

	private String id;
	private List<SceneAction<WorldContext>> actions;
	private BiConsumer<String, WorldContext> defaultAction;
	private boolean blocked;

	public WorldScene(String id) {
		this.id = id;
		this.actions = new ArrayList<>();
	}
	
	@Override
	public String getID() {
		return id;
	}
	@Override
	public void onSceneEntry(WorldContext context) { }
	@Override
	public void onSceneExit(WorldContext context) { }

	@Override
	public void perform(String command, WorldContext context) {
		if(blocked) {
			blocked = false;
			return;
		}
		
		command = command == null ? "" : command.trim().replaceAll("[ \t\n]+", " ").toLowerCase();
		
		BiConsumer<String, WorldContext> action = null;
		for(SceneAction<WorldContext> a : getActions()) {
			if(a != null && a.check(command, context)) {
				action = a;
				break;
			}
		}
		if(action == null && context != null) {
			WorldContext ctx = context;
			int count = ctx.getGlobalActionCount();
			for(int i = 0; i < count; i++) {
				SceneAction<WorldContext> a = ctx.getGlobalAction(i);
				if(a != null && a.check(command, context)) {
					action = a;
					break;
				}
			}
		}
		
		if(action == null)
			action = getDefaultAction();
		if(action == null && context != null)
			action = context.getGlobalDefaultAction();
		
		if(action != null)
			action.accept(command, context);
		else
			throw new IllegalArgumentException("Command is not registered: " + command);
	}
	
	protected void waitForSkip() {
		blocked = true;
		while(blocked) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				blocked = false;
			}
		}
	}
	protected void stopWaitForSkip() {
		blocked = true;
	}

	protected List<SceneAction<WorldContext>> getActions() {
		return actions;
	}

	protected BiConsumer<String, WorldContext> getDefaultAction() {
		return defaultAction;
	}
	protected void setDefaultAction(BiConsumer<String, WorldContext> defaultAction) {
		this.defaultAction = defaultAction;
	}
	
	protected void addAction(String regex, BiConsumer<String, WorldContext> action) {
		getActions().add(new SceneAction<WorldContext>() {
			
			@Override
			public void accept(String t, WorldContext u) {
				action.accept(t, u);
			}
			@Override
			public boolean check(String command, WorldContext context) {
				return (command == null ? "" : command).matches(regex);
			}
		});
	}
}
