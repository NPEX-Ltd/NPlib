package np.library.testing.tests;

import java.io.File;

import np.library.io.ClassFinder;
import np.library.testing.Test;
import np.library.testing.Tester;

public class ClassFinderTests {
	@Test
	public void testListClasses() {
		Tester.FailIfNull(ClassFinder.ListClasses(new File("resources/")));
	}
}
