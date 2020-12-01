package np.library.common;

public class Timer {
	private long start, end;
	public void Start() {
		start = System.currentTimeMillis();
	}
	public float Stop() {
		end = System.currentTimeMillis();
		return (end - start) / 1000f;
	}
	public float GetTimeSeconds() {
		Stop();
		float timeDifference = (end - start) / 1000f;
		Start();
		return timeDifference;
	}
}
