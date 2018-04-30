package slackbot.feed.utils.mappers;

import slackbot.feed.utils.news.SlackMessage;

public interface SlackMessageMapper<T> {

	public SlackMessage toSlackMessage(T t);

}
