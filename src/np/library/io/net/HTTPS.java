package np.library.io.net;

import java.io.*;
import java.net.*;
import java.util.Map;

import np.library.exceptions.JuggledException;

public class HTTPS {
	
	private static final int
	OK = 200,
	BAD_METHOD = 405,
	FILE_NOT_FOUND = 404;


	public static String Get(String site, Map<String, String> params) {
		return Communicate("GET", site, params);
	}
	
	public static String Post(String site, Map<String, String> params) {
		return Communicate("POST", site, params);
	}
	
	private static String Communicate(String method, String site, Map<String, String> params) {
		HttpURLConnection con = OpenHTTP(site);
		WriteHTTPS("GET", params, con);
		return ReadHTTPS("GET", con);
	}
	
	private static void WriteHTTPS(String method, Map<String, String> params, HttpURLConnection con) {
		try {
		con.setRequestMethod(method);
		
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		if(params != null) {
			out.writeBytes(ParameterStringBuilder.getParamsString(params));
		}
		out.flush();
		out.close();
		} catch (IOException ioex) {
			throw new JuggledException(ioex);
		}
	}
	
	private static String ReadHTTPS(String method, HttpURLConnection con) {
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine).append('\n');
				}
				in.close();
				con.disconnect();
				return content.toString();
			} else {
				System.err.println("Received Error Code "+responseCode+" From "+con.getURL().getHost());
				throw new BadHttpResponseException(responseCode);
			}
		} catch (IOException ioex) {
			throw new JuggledException(ioex);
		}
	}
	
	public static HttpURLConnection OpenHTTP(String site) {
		try {
		URL url = new URL(site);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		return con;
		} catch (IOException ioex) {
			throw new JuggledException(ioex);
		}
	}
	
	
	public static class ParameterStringBuilder {
	    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException{
	        StringBuilder result = new StringBuilder();

	        for (Map.Entry<String, String> entry : params.entrySet()) {
	          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	          result.append("=");
	          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	          result.append("&");
	        }

	        String resultString = result.toString();
	        return resultString.length() > 0
	          ? resultString.substring(0, resultString.length() - 1)
	          : resultString;
	    }
	}
}
