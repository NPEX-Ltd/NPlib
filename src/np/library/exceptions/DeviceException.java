package np.library.exceptions;

public class DeviceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DeviceException() {}
	public DeviceException(String message) {super(message);}
}
