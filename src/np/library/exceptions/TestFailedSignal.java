package np.library.exceptions;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class TestFailedSignal extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String method;
	private int lineNum = 0;
	private String causer;

	public TestFailedSignal(String message) {
		super(message);
		super.fillInStackTrace();
		this.method = getStackTrace()[1].getMethodName();
		this.lineNum = getStackTrace()[1].getLineNumber();
		this.causer = getStackTrace()[1].getClassName();
	}

	public String getMethod() {
		return method;
	}

	public String getCauser() {
		return causer;
	}
	
	public int GetLineNum() {
		return lineNum;
	}
	
	public String GetMessage() {
		return "["+causer+"."+method+":"+lineNum+"]: "+super.getMessage();
	}
	
	
}