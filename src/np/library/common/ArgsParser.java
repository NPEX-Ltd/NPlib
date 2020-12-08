package np.library.common;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class ArgsParser {
	public static boolean ArgsContainsFlag(String[] args, String flag) {
		for(String arg : args) {
			if(arg.equals(flag)) return true;
		}
		return false;
	}
}
