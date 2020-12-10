package np.library.common;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class Time {
	public static void SleepMillis(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException intex) {}
	}
}
