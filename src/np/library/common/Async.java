package np.library.common;

public class Async {
	public static void DispatchThread(String name, boolean isDaemon, Runnable job) {
		Thread t = new Thread(job);
		t.setDaemon(isDaemon);
		t.setName(name);
		t.start();
	}
}
