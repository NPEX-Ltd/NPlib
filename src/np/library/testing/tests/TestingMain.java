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
		if(ArgsParser.ArgsContainsFlag(args, "-net")) Tester.Test(NetworkTests.class);
		Tester.Test(ClassFinderTests.class);
		Tester.Test(XMLTester.class);
		Tester.Test(AsyncTests.class);
<<<<<<< HEAD
		if(ArgsParser.ArgsContainsFlag(args, "-net")) Tester.Test(HTTPTester.class);
=======
		Tester.Test(HTTPTester.class);
>>>>>>> 6c6d5e341aae3015bba19adeb884344ac5071e7b
		Tester.Test(AsyncTests.class);
	}
}
