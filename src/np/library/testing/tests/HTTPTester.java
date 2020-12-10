package np.library.testing.tests;

import np.library.io.net.BadHttpResponseException;
import np.library.io.net.HTTPS;
import np.library.io.net.HTTPS.Request;
import np.library.testing.Test;

public class HTTPTester {
	@Test
	public void testGetRequest() {
		System.out.println(HTTPS.Get("http://example.com", null));
	}
	
	@Test
	public void testGetHTTPSRequest() {
//		Request req = Request.BuildGetRequest("https://example.com",null);
//		System.out.println(HTTPS.SendRequest(req));
		System.out.println("=========================================");
		System.out.println(HTTPS.SendRequest(
				Request.Build(
						"https://api.wheretheiss.at/v1/satellites", "", "", "FETCH", null
				)
		));
	}
	
	@Test
	public void testGetBadResponse() throws BadHttpResponseException {
		System.out.println(HTTPS.Get("https://example.com/ja", null));
	}
}
