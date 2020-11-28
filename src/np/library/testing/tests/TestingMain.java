package np.library.testing.tests;

import java.io.File;
import java.io.IOException;

import np.library.io.FileWatcher;
import np.library.testing.Tester;

public class TestingMain {

	public static void main(String[] args) {
		if(Tester.Test(LoggerTests.class)) System.out.println("LoggerTests Passed!");
		FileWatcher watcher = null;
		try {
			watcher = new TestFileWatcher();
			watcher.RegisterDir(new File("resources/"));
			watcher.StartPollingForEvents();
		} catch(IOException ioex) {
			Tester.Fail("An IOException has occurred...");
		}
		if(Tester.Test(DeviceTests.class)) System.out.println("DeviceTests Passed!");
		
		watcher.StopPolling();
	}
	
	public static class TestFileWatcher extends FileWatcher {

		public TestFileWatcher() throws IOException {
			super();
		}

		@Override
		protected void OnFileCreated(File file) {
			if(file.isDirectory()) RegisterDir(file);
			System.out.println(file.getPath()+" Created");
			
			if(!file.getPath().equals("file.txt")) {
				System.exit(-1);
			} else {
				System.out.println("FileWatcher Tests Passed!");
			}
		}

		@Override
		protected void OnFileModified(File file) {
			System.out.println(file.getPath()+" Edited");
			if(!file.getPath().equals("file.txt")) {
				System.exit(-1);
			} else {
				System.out.println("FileWatcher Tests Passed!");
			}
		}

		@Override
		protected void OnFileDeleted(File file) {
			System.out.println(file+" Deleted");
		}
		
		
		
		
	}

}
