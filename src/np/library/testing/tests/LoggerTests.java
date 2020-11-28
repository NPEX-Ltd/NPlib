package np.library.testing.tests;

import np.library.common.Logger;
import np.library.testing.Test;


public class LoggerTests {
	
	Logger logger = np.library.common.Logger.CreateNew(LoggerTests.class);
	
	@Test
	public void TestInfo() {
		logger.Warn("INFO");
	}
	
	@Test
	public void TestDebug() {
		logger.Warn("DEBUG");
	}
	
	@Test
	public void TestWarn() {
		logger.Warn("WARNING");
	}
}
