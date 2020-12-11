package np.library.io;
import java.io.*;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.FileCreationException;
@API(level = Level.ALPHA)
public class IOUtils {
	
	private IOUtils() {}
	
	public static boolean FileExists(String file) {
		return new File(file).exists();
	}
	
	public static boolean MakeDirectory(String dir)
	throws FileCreationException {
		return ToFile(dir).mkdir();
	}
	
	public static String[] LoadLines(String path)
	throws FileCreationException {
		return LoadText(path).split("\n");
	}
	
	public static String LoadText(String path)
	throws FileCreationException {
		FileIO file = OpenFile(path);
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while((line = file.ReadStringOrBlock()) != null) {
			buffer.append(line + "\n");
		}
		file.Close();
		return buffer.toString();
	}
	
	public static FileIO OpenFile(String file)
	throws FileCreationException {
		return new FileIO(ToFile(file));
	}
	
	public static File ToFile(String path)
	throws FileCreationException {
		try {
			File file = new File(path);
			file.createNewFile();
			return file;
		} catch (IOException ioex) {
			throw new FileCreationException(ioex);
		}
	}
}
