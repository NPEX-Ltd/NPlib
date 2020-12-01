package np.library.common.strings;

import java.util.*;

public abstract class StringFormatter {
	private Map<String, Formatter> formatters = new HashMap<String, Formatter>();
	private Formatter defaultFormatter = new Formatter() {
		@Override
		public String replace(String key, Object... args) {
			return key;
		}
	};
	public String Format(String format, Object... args) {
		
		String[] tokens = format.split("[\\s]+");
		String output = "";
		for(String token : tokens) {
			token = formatters.getOrDefault(token, defaultFormatter).replace(token, args);
			output += token + " ";
		}
		
		return output;
	}
	
	
	
	public static interface Formatter {
		public String replace(String key, Object... args);
	}
}
