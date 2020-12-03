package np.library.testing.tests;

import np.library.testing.Tester;

public class TestingMain {

	public static void main(String[] args) {
		Tester.Test(LoggerTests.class);
		Tester.Test(DeviceTests.class);
		Tester.Test(NetworkTests.class);
		Tester.Test(ClassFinderTests.class);
		Tester.Test(XMLTester.class);
	}
}
