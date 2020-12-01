package np.library.common;

public class Time {
	public static void SleepMillis(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException intex) {}
	}
}
