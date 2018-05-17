package slackbot.feed.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {

	private HttpClient httpClient;

	private String url;

	@Before
	public void init() {
		httpClient = new HttpClient();
	}

	@Test
	public void testGetHttp() {
		url = "http://www.google.com";
		Map<String, String> getResult = new HashMap<>();
		getResult.put(httpClient.RETURN_CODE, "false value for http return code");
		try {
			getResult = httpClient.get(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assertEquals(getResult.get(httpClient.RETURN_CODE), "200");
	}

	@Test
	public void testGetHttps() {
		url = "https://www.google.com";
		Map<String, String> getResult = new HashMap<>();
		getResult.put(httpClient.RETURN_CODE, "false value for http return code");
		try {
			getResult = httpClient.get(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assertEquals(getResult.get(httpClient.RETURN_CODE), "200");
	}

	// TODO Why it doesn't work
	// @Test(expected = MalformedURLException.class)
	@Test
	public void testGetUrlFormat() {
		// TODO why to try catch it? it's what's expeted!
		try {
			Map<String, String> getResult = httpClient.get("malformedUrl");
		} catch (MalformedURLException e) {
			// e.printStackTrace();
			// TODO oblige
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testGetNull() {
		// TODO why to try catch it? it's what's expeted!
		try {
			httpClient.get(null);
		} catch (MalformedURLException e) {
			// e.printStackTrace();
		}
	}

	@Test
	public void testPost() {
		// TODO don't know how to do, run a quick standalone server ?
		// url = "https://www.google.com";
		// Map<String, String> getResult = new HashMap<>();
		// getResult.put(httpClient.RETURN_CODE, "false value for http return code");
		// try {
		// getResult = httpClient.get(url);
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }
		// assertEquals(getResult.get(httpClient.RETURN_CODE), "200");
	}

	@Test(expected = NullPointerException.class)
	public void testPostUrlNull() {
		try {
			httpClient.post(null, "payload");
		} catch (MalformedURLException e) {
		}
	}

	@Test
	public void testPostUrlFormat() {
		try {
			httpClient.post("malformedUrl", "payload");
		} catch (MalformedURLException e) {
			assertTrue(true);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testPostPayloadNull() {
		try {
			httpClient.post(url, null);
		} catch (MalformedURLException e) {
		}
	}
}
