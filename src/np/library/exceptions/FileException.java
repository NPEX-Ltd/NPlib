package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class FileException extends JuggledException {
	public FileException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;
}
