package slackbot.feed.writers;

import java.net.MalformedURLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("text", news.getDescription());
		objectNode.put("channel", news.getLink());

		Map<String, String> content = httpClient.post(cible, objectNode.toString());
		returnValue = Integer.parseInt(content.get(HttpClient.RETURN_CODE));

		return returnValue;

	}

}
