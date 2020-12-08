package np.library.common;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JNAUtils {
	public static <T extends Library> T LoadLibrary(String path, Class<T> lib) {
		return Native.load(path, lib);
	}
	
	public static void SetLibraryPath(String path) {
		System.setProperty("jna.library.path", path);
	}
}
