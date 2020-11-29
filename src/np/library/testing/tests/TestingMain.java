package np.library.testing.tests;

import java.io.File;
import java.io.IOException;

import np.library.io.FileWatcher;
import np.library.testing.Tester;

public class TestingMain {

	public static void main(String[] args) {
		Tester.Test(LoggerTests.class);
		Tester.Test(DeviceTests.class);
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
