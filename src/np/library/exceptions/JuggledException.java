package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class JuggledException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JuggledException(Throwable cause) {
		this.initCause(cause);
	}
}
