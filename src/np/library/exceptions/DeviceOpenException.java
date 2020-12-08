package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class DeviceOpenException extends DeviceException {
	public DeviceOpenException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;
}
