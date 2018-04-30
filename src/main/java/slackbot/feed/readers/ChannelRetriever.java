package slackbot.feed.readers;

import java.util.List;

import slackbot.feed.utils.channel.Channel;
import slackbot.feed.utils.news.Article;

public class ChannelRetriever implements InformationsRetriever<Article, Channel> {

	@Override
	public List<Article> retrieveInformations(Channel source) {
		return null;
	}

}
