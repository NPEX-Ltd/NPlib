package np.library.gui.component.builders;

public class ArgsParser {
	public static boolean ArgsContainsFlag(String[] args, String flag) {
		for(String arg : args) {
			if(arg.equals(flag)) return true;
		}
		return false;
	}
}
