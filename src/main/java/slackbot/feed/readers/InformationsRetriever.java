package slackbot.feed.readers;

import java.net.MalformedURLException;
import java.util.List;

import slackbot.feed.utils.news.News;

public interface InformationsRetriever<T extends News, U> {

	public List<T> retrieveInformations(U source) throws MalformedURLException;
}
