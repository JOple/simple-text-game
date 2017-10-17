package old;

public class WorldSceneAbortException extends RuntimeException implements Runnable {

	private static final long serialVersionUID = -1634924971183892374L;

	private Runnable postAction;

	public WorldSceneAbortException() { }
	public WorldSceneAbortException(Runnable postAction) {
		this.postAction = postAction;
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
