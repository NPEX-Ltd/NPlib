package np.library.exceptions;

import java.io.IOException;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class FileCreationException extends FileException {
	public FileCreationException(IOException cause) {
		super(cause);
	}
	private static final long serialVersionUID = 1L;
}
