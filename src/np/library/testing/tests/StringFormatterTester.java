package np.library.testing.tests;

import np.library.common.Async;
import np.library.common.strings.StringFormatter;
import np.library.testing.Test;

public class StringFormatterTester {
	@Test
	public void testFormatting() {
		String test = "${Thread.name}";
		StringFormatter formatter = new StringFormatter();
		formatter.RegisterDataProvider("\\Q${Thread.name}\\E", this::threadNameProvider);
		System.out.println(formatter.Format(test));
	}
	
	private String threadNameProvider(String info) {
		return Async.GetThreadName();
	}
}
