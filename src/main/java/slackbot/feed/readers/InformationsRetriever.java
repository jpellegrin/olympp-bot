package slackbot.feed.readers;

import java.util.List;

import slackbot.feed.utils.news.News;

public interface InformationsRetriever<T extends News, U> {

	public List<T> retrieveInformations(U source);
}
