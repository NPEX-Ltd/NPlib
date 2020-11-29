package np.library.testing.tests;

import np.library.common.Async;
import np.library.io.NetworkIO;
import np.library.testing.Test;
import np.library.testing.Tester;

public class NetworkTests {
	@Test
	public void testNetwork() {
		
		Async.DispatchThread("Network Testing Thread", false, () -> { BasicServer.main(null); });
		
		NetworkIO device = new NetworkIO("localhost", 5000);
		device.WriteString("Hello");
		Tester.FailIfNotEqual("Hello", device.ReadStringOrBlock());
	}
}
