package np.library.common;

public class Async {
	
	private static int daemonThreadID = 0;
	
	public static void DispatchThread(String name, boolean isDaemon, Runnable job) {
		Thread t = new Thread(job);
		t.setDaemon(isDaemon);
		t.setName(name);
		t.start();
	}
	
	public static void DispatchDaemon(String name, Runnable job) {
		DispatchThread(name+" (Daemon-"+(daemonThreadID++)+")", true, job);
	}
	
	public static String GetThreadName() { return Thread.currentThread().getName(); }
}
