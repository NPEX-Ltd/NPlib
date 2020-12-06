package np.library.testing.tests;

import np.library.io.net.BadHttpResponseException;
import np.library.io.net.HTTPS;
import np.library.testing.Test;

public class HTTPTester {
	@Test
	public void testGetRequest() {
		System.out.println(HTTPS.Get("http://example.com", null));
	}
	
	@Test
	public void testGetHTTPSRequest() {
		System.out.println(HTTPS.Get("https://example.com", null));
	}
	
	@Test
	public void testGetBadResponse() throws BadHttpResponseException {
		System.out.println(HTTPS.Get("https://example.com/ja", null));
	}
}
