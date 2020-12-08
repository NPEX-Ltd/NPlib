package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class DeviceReadException extends DeviceException {
	public DeviceReadException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;
}
