package np.library.testing.tests;

import np.library.common.Async;
import np.library.testing.Test;
import np.library.testing.Tester;

public class AsyncTests {
	@Test
	public void testDispatchThread() {
		Async.DispatchThread("TEST_THREAD", true, () -> {
			System.out.println("Hello From "+Async.GetThreadName());
			Tester.FailIfNotEqual("TEST_THREAD", Async.GetThreadName());
		});
	}
}
