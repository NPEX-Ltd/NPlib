package np.library.exceptions;

import java.io.IOException;

public class FileCreationException extends FileException {
	public FileCreationException(IOException cause) {
		super(cause);
	}
	private static final long serialVersionUID = 1L;
}
