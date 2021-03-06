package np.library.common;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class Async {
	
	private static int daemonThreadID = 0;
	
	public static Thread DispatchThread(String name, boolean isDaemon, Runnable job) {
		Thread t = new Thread(job);
		t.setDaemon(isDaemon);
		t.setName(name);
		t.start();
		return t;
	}
	
	public static Thread DispatchDaemon(String name, Runnable job) {
		return DispatchThread(name+" (Daemon-"+(daemonThreadID++)+")", true, job);
	}
	
	public static String GetThreadName() { return Thread.currentThread().getName(); }

	public static void Yield() {
		Thread.yield();
	}
}
