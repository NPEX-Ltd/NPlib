package np.library.testing.tests;

import java.io.File;
import java.io.IOException;

import np.library.io.FileWatcher;
import np.library.testing.Tester;

public class TestingMain {

	public static void main(String[] args) {
		Tester.Test(LoggerTests.class);
		Tester.Test(DeviceTests.class);
		Tester.Test(NetworkTests.class);
	}
}
