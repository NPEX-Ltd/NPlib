package np.library.exceptions;

public class TestFailedSignal extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TestFailedSignal(String message) {
		super(message);
	}
}