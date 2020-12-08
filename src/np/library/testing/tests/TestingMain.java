package np.library.testing.tests;

import java.util.Arrays;

import np.library.common.ArgsParser;
import np.library.testing.Tester;

public class TestingMain {
	public static boolean ANT_MODE = true;
	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		ANT_MODE = ArgsParser.ArgsContainsFlag(args, "-ant");
		System.out.println("Running In Ant Mode? "+ANT_MODE);
		Tester.Test(LoggerTests.class);
		Tester.Test(DeviceTests.class);
		Tester.Test(NetworkTests.class);
		Tester.Test(ClassFinderTests.class);
		Tester.Test(XMLTester.class);
		//Tester.Test(SwingXMLTester.class);
		Tester.Test(AsyncTests.class);
		Tester.Test(HTTPTester.class);
		Tester.Test(StringFormatterTester.class);
		Tester.Test(AsyncTests.class);
		//Tester.Test(HTTPTester.class);
		//Tester.Test(JNATester.class);
	}
}
