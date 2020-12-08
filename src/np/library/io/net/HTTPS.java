package np.library.io.net;

import java.io.*;
import java.net.*;
import java.util.Map;

import np.library.exceptions.JuggledException;

public class HTTPS {
	
	private static final int
	OK = 200,
	UNAUTHORISED = 401,
	BAD_METHOD = 405,
	FILE_NOT_FOUND = 404;


	public static String Get(String site, Map<String, String> params) {
		return Communicate("GET", site, params);
	}
	
	public static Response SendRequest(Request request) {
		Response response = new Response();
		try {
			response.content = Communicate(request.method, request.url, request.params);
			response.responseCode = OK;
		} catch (BadHttpResponseException bhrex) {
			response.responseCode = bhrex.getCode();
		}
		return response;
	}
	
	public static Response Get(Request request) {
		Response response = new Response();
		try {
			response.content = Get(request.url, request.params);
			response.responseCode = OK;
		} catch (BadHttpResponseException bhrex) {
			response.responseCode = bhrex.getCode();
		}
		return response;
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
				System.out.println("Received Error Code "+responseCode+" From "+con.getURL().getHost());
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
	    public static String getParamsString(Map<String, String> params){
	    	try {
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
		    } catch (UnsupportedEncodingException ueex) {
		    	throw new JuggledException(ueex);
		    }
	    }
	}
	
	public static class Request {
		public Map<String, String> params;
		public String header, contentType, method;
		public String url;
		
		public static Request Build(String url, String header, String contentType, String method, Map<String, String> params) {
			return new Request(header, params, contentType, method, url);
		}
		
		private Request(String header, Map<String, String> body, String contentType, String method, String url) {
			super();
			this.header = header;
			this.params = body;
			this.contentType = contentType;
			this.method = method;
			this.url = url;
		}

		public static Request BuildGetRequest(String string, Map<String, String> params){
			return Build(string, null, "GET", null, params);
		}
	}
	
	public static class Response {
		public String content, url;
		public int responseCode;
		
		@Override
		public String toString() {
			return "["+responseCode+"]: "+content;
		}
	}
}
