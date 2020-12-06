package np.library.exceptions;

public class JuggledException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JuggledException(Throwable cause) {
		this.initCause(cause);
	}
}
