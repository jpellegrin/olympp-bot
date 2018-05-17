package slackbot.feed.wriers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import slackbot.feed.utils.HttpClient;
import slackbot.feed.utils.news.SlackMessage;
import slackbot.feed.writers.WebhookPoster;

public class WebhookPosterTest {

	@InjectMocks
	private WebhookPoster webhookPoster;

	@Mock
	private HttpClient httpClient;

	private String title = "SlackMessage title test";
	private String description = "SlackMessage description for test";
	private String link = "SlackMessage link or more presicely a slack channel test";
	private SlackMessage slackMessage;

	private String cible = "cilbe url to post for test";

	private Map<String, String> httpClient_result;
	private String returnCode = "200";
	private String returnPayload = "payload";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		slackMessage = new SlackMessage();
		slackMessage.setTitle(title);
		slackMessage.setLink(link);
		slackMessage.setDescription(description);

		httpClient_result = new HashMap<>();
		httpClient_result.put(HttpClient.RETURN_CODE, returnCode);
		httpClient_result.put(HttpClient.RETURN_PAYLOAD, returnPayload);
	}

	@Test
	public void postTest() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("text", description);
			jsonObject.put("channel", link);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			when(httpClient.post(link, jsonObject.toString())).thenReturn(httpClient_result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Integer result = 0;
		try {
			result = webhookPoster.post(link, slackMessage);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assertEquals(result.toString(), returnCode);
	}

	@Test(expected = NullPointerException.class)
	public void postTestCibleNull() {
		try {
			webhookPoster.post(null, slackMessage);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	// TODO
	@Test
	public void postTestCibleUrlFormat() {
		try {
			webhookPoster.post("Malformed url for test", slackMessage);
		} catch (MalformedURLException e) {
			assertTrue(true);
		}

	}

	@Test(expected = NullPointerException.class)
	public void TestSlackMessageNull() {
		try {
			webhookPoster.post(link, null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
