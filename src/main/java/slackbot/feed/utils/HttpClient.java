package slackbot.feed.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class HttpClient {

	public static final String RETURN_CODE = "return_code";
	public static final String RETURN_PAYLOAD = "return_payload";

	public Map<String, String> get(String url) throws MalformedURLException {
		if (url == null) {
			throw new NullPointerException("url is null");
		}

		Map<String, String> mapResponse = new HashMap<>();
		mapResponse.put(RETURN_CODE, "404");

		BufferedReader br = null;

		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			mapResponse.put(RETURN_CODE, Integer.toString(connection.getResponseCode()));

			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}

			mapResponse.put(RETURN_PAYLOAD, sb.toString());

		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			mapResponse.put(RETURN_CODE, "404");
			mapResponse.put(RETURN_PAYLOAD, e.toString());
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					if (mapResponse.get(RETURN_PAYLOAD) == null) {
						mapResponse.put(RETURN_PAYLOAD, e.toString());
						e.printStackTrace();
					}
				}
			}
		}

		return mapResponse;
	}

	public Map<String, String> post(String url, String payload) throws MalformedURLException {
		if (url == null) {
			throw new NullPointerException("url is null");
		}
		if (payload == null) {
			throw new NullPointerException("payload is null");
		}

		BufferedReader br = null;

		Map<String, String> mapResponse = new HashMap<>();
		mapResponse.put(RETURN_CODE, "404");

		try {
			URL urlCible = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) urlCible.openConnection();
			connection.setRequestMethod("POST");

			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(payload.getBytes());
			os.flush();
			os.close();

			mapResponse.put(RETURN_CODE, Integer.toString(connection.getResponseCode()));

			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}

			mapResponse.put(RETURN_PAYLOAD, sb.toString());
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			mapResponse.put(RETURN_CODE, "404");
			mapResponse.put(RETURN_PAYLOAD, e.toString());
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					if (mapResponse.get(RETURN_PAYLOAD) == null) {
						mapResponse.put(RETURN_PAYLOAD, e.toString());
						e.printStackTrace();
					}
				}
			}
		}

		return mapResponse;
	}

}
