package slackbot.bot;

import slackbot.feed.utils.mappers.SlackMessageMapper;
import slackbot.feed.utils.news.Article;
import slackbot.feed.utils.news.SlackMessage;

public class SlackMapper implements SlackMessageMapper<Article> {

	@Override
	public SlackMessage toSlackMessage(Article t) {
		return null;
	}

}
