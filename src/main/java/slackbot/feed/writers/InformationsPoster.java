package slackbot.feed.writers;

import slackbot.feed.utils.news.News;

public interface InformationsPoster<T, U extends News> {

	boolean post(T cible, U news);
}
