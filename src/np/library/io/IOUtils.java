package np.library.io;
import java.io.*;

import np.library.exceptions.FileCreationException;
public class IOUtils {
	public static boolean FileExists(String file) {
		return ToFile(file).exists();
	}
	
	public static boolean MakeDirectory(String dir) {
		return ToFile(dir).mkdir();
	}
	
	public static FileIO OpenFile(String file) {
		return new FileIO(ToFile(file));
	}
	 
	public static File ToFile(String path) {
		try {
			File file = new File(path);
			file.createNewFile();
			return file;
		} catch (IOException ioex) {
			throw new FileCreationException();
		}
	}
}
