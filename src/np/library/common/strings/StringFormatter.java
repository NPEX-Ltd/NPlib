package np.library.common.strings;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {
	
	private Map<String, DataProvider<String>> dataProviders = new HashMap<>();
	
	public String Format(String text, Object... args) {
		text = String.format(text, args);
		for(String regex : dataProviders.keySet()) {
			List<String> matches = GetAllMatches(regex, text);
			//System.out.println(matches);
			for(String match : matches) {
				DataProvider<String> provider = dataProviders.getOrDefault(match, (String info) -> {return info;});
				String replacement = provider.Invoke(match);
				text = LiteralReplace(text, match, replacement);
			}
		}
		return text;
	}
	
	public void RegisterDataProvider(String regex, DataProvider<String> provider) {
		dataProviders.put(regex, provider);
		
	}
	 
	private List<String> GetAllMatches(String pattern, String text) {
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(text);
		
		List<String> list = new ArrayList<>();
		while(matcher.find()) {
			list.add(matcher.group());
		}
		
		return list;
	} 
	
	private String LiteralReplace(String text, String item, String replacement) {
		item = "\\Q" + item + "\\E";
		replacement = "\\Q" + replacement + "\\E";
		
		return text.replaceAll(item, replacement);
	}
	
	public static interface DataProvider<T> {
		public T Invoke(String info);
	}
}
