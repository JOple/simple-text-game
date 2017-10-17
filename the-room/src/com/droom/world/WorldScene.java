package com.droom.world;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.droom.Scene;
import com.droom.SceneCommand;

public abstract class WorldScene implements Scene, WorldMacros, DefaultWorldActions {

	public static class FlowControlException  extends RuntimeException implements Runnable {

		private static final long serialVersionUID = 1279955991416471621L;

		private String flowControlType;
		private Runnable postAction;

		public FlowControlException(String flowControlType) {
			this.flowControlType = flowControlType;
		}
		public FlowControlException(String flowControlType, Runnable postAction) {
			this(flowControlType);
			this.postAction = postAction;
		}

		public String getFlowControlType() {
			return flowControlType;
		}
		public Runnable getPostAction() {
			return postAction;
		}

		@Override
		public void run() {
			if(postAction != null)
				postAction.run();
		}
	}

	private Image defaultImage;

	public WorldScene(Image img) {
		this.defaultImage = img;
	}

	@Override
	public void initialization() {
		World.show(defaultImage);
	}
	@Override
	public void finalization() { }

	protected void defaultAction(SceneCommand cmd) {
		say("I don't understand");
	}

	protected void flowControl(String type) {
		throw new FlowControlException(type);
	}
	protected void flowControl(String type, Runnable postAction) {
		throw new FlowControlException(type, postAction);
	}

	private boolean eventMethodInvoke(SceneCommand cmd, Method method) {
		try {
			Class<?>[] params = method.getParameterTypes();
			switch(params.length) {
			case 0:
				method.invoke(this);
				return true;
			case 1:
				if(SceneCommand.class.isAssignableFrom(params[0])) {
					method.invoke(this, cmd);
					return true;
				} else if(String[].class.isAssignableFrom(params[0])) {
					method.invoke(this, new Object[] {cmd.getArguments()});
					return true;
				} else if(CharSequence.class.isAssignableFrom(params[0])) {
					method.invoke(this, cmd.getArgument());
					return true;
				}
				break;
			case 2:
				if(CharSequence.class.isAssignableFrom(params[0]) && CharSequence.class.isAssignableFrom(params[1])) {
					method.invoke(this, cmd.getArgument(), cmd.getRawCommand());
					return true;
				} else if(String[].class.isAssignableFrom(params[0]) && CharSequence.class.isAssignableFrom(params[1])) {
					method.invoke(this, cmd.getArguments(), cmd.getRawCommand());
					return true;
				} else if(CharSequence.class.isAssignableFrom(params[0]) && String[].class.isAssignableFrom(params[1])) {
					method.invoke(this, cmd.getRawCommand(), cmd.getArguments());
					return true;
				}
				break;
			}
		} catch(Exception e) {
			if(e instanceof InvocationTargetException && ((InvocationTargetException) e).getCause() instanceof WorldScene.FlowControlException)
				throw (WorldScene.FlowControlException) e.getCause();
			else
				throw new RuntimeException("Error invoking method", e);
		}
		return false;
	}

	private static final String METHOD_PREFIX = "onCmd";
	private static final String METHOD_ACTION_DEFAULT = "Default";
	private static final String METHOD_ARGUMENT_EMPTY = "";
	private static final String METHOD_ARGUMENT_UNKOWN = "Unknown";
	@Override
	public void perform(SceneCommand cmd) {
		boolean success = false;
		String prefixName = METHOD_PREFIX + (cmd.getAction() == null ? METHOD_ACTION_DEFAULT : cmd.getAction());
		String targetName = (prefixName + ((cmd.getArgument() == null || cmd.getArgument().length() == 0) ? METHOD_ARGUMENT_EMPTY :
			cmd.getArgument()
			.trim()
			.replaceAll("\\p{javaSpaceChar}",""))).toLowerCase();
		String unkName = (prefixName + METHOD_ARGUMENT_UNKOWN).toLowerCase();
		Method unkMethod = null;
		for(Method method : getClass().getMethods()) {
			String methodName = method.getName().toLowerCase();
			if(methodName.equals(targetName)) {
				success = eventMethodInvoke(cmd, method);
				if(success)
					break;
			}
			if(methodName.equals(unkName))
				unkMethod = method;
		}
		if(!success && unkMethod != null)
			success = eventMethodInvoke(cmd, unkMethod);
		if(!success)
			defaultAction(cmd);
	}
}
