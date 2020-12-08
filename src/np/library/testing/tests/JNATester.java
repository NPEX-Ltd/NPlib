package np.library.testing.tests;

import com.sun.jna.Library;

import np.library.common.JNAUtils;
import np.library.testing.Test;

public class JNATester {
	public static interface TestLib extends Library {
		public void Greet();
	}
	
	@Test
	public void testGreeter() {
		JNAUtils.SetLibraryPath("/natives");
		TestLib lib = JNAUtils.LoadLibrary("TestLib", TestLib.class);
		lib.Greet();
	}
	
}
