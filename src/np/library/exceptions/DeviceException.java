package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class DeviceException extends JuggledException {
	private static final long serialVersionUID = 1L;
	
	public DeviceException(Throwable cause) { super(cause); }
}
