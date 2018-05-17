package slackbot.feed.writers;

import java.net.MalformedURLException;

import slackbot.feed.utils.news.News;

public interface InformationsPoster<T, U extends News> {

	Integer post(T cible, U news) throws MalformedURLException;
}
