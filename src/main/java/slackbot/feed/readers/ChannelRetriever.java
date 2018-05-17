package slackbot.feed.readers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import slackbot.feed.utils.HttpClient;
import slackbot.feed.utils.news.Article;
import slackbot.feed.utils.xmlparser.Channel;
import slackbot.feed.utils.xmlparser.XMLParser;

@Component
public class ChannelRetriever implements InformationsRetriever<Article, String> {

	@Autowired
	private XMLParser<Channel> xmlParser;

	@Autowired
	private HttpClient httpClient;

	public List<Article> retrieveInformations(String source) throws MalformedURLException {
		if (source == null) {
			throw new NullPointerException("source is null");
		}

		List<Article> articles = new ArrayList<>();

		Map<String, String> content = httpClient.get(source);

		if (content.get(HttpClient.RETURN_CODE) == "200") {
			Channel channel = xmlParser.parse(content.get(HttpClient.RETURN_PAYLOAD));
			articles = channel.getArticles();
		}

		return articles;
	}

}
