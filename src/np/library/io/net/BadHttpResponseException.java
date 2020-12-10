package np.library.io.net;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class BadHttpResponseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code = 0;
	public BadHttpResponseException(int responseCode) {
		super("HTTP Response Code: "+responseCode);
		this.code = responseCode;
	}
	public int getCode() { return code; }
}
