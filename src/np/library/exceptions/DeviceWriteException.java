package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class DeviceWriteException extends DeviceException {
	public DeviceWriteException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;
}
