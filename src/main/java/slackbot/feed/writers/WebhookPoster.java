package slackbot.feed.writers;

import slackbot.data.jpa.model.Webhook;
import slackbot.feed.utils.news.SlackMessage;

public class WebhookPoster implements InformationsPoster<Webhook, SlackMessage> {

	public boolean post(Webhook cible, SlackMessage news) {
		return false;
	}

}
