package slackbot.feed.writers;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import slackbot.feed.utils.HttpClient;
import slackbot.feed.utils.news.SlackMessage;

@Component
public class WebhookPoster implements InformationsPoster<String, SlackMessage> {

	@Autowired
	private HttpClient httpClient;

	public Integer post(String cible, SlackMessage news) throws MalformedURLException {
		if (cible == null) {
			throw new NullPointerException("cible is null");
		}
		if (news == null) {
			throw new NullPointerException("news to post is null");
		}

		Integer returnValue = 500;

		JSONObject jsonPayload = new JSONObject();
		try {
			jsonPayload.put("text", news.getDescription());
			jsonPayload.put("channel", news.getLink());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Map<String, String> content = httpClient.post(cible, jsonPayload.toString());
		// System.out.println(content.toString());
		returnValue = Integer.parseInt(content.get(HttpClient.RETURN_CODE));

		return returnValue;

	}

}
