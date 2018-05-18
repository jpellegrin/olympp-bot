package slackbot.bot;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import slackbot.data.jpa.model.Rssfeed;
import slackbot.data.jpa.repositories.RssfeedRepository;
import slackbot.data.jpa.repositories.WebhookRepository;
import slackbot.feed.readers.InformationsRetriever;
import slackbot.feed.utils.mappers.SlackMessageMapper;
import slackbot.feed.utils.news.Article;
import slackbot.feed.utils.news.SlackMessage;
import slackbot.feed.writers.InformationsPoster;

@Component
public class RssfeedReaderAndSlackPoster {

	@Autowired
	private RssfeedRepository rssfeedRepository;

	@Autowired
	private InformationsRetriever<Article, String> articlesRetriever;

	@Autowired
	private WebhookRepository webhookRepository;

	@Autowired
	private SlackMessageMapper<Article> slackMapper;

	@Autowired
	private InformationsPoster<String, SlackMessage> slackWebhookPoster;

	@Scheduled(cron = "0 */10 * * * *")
	public void retrieveFromRssfeedAndSendToSlack() {

		List<Rssfeed> rssfeeds = rssfeedRepository.findAll();

		rssfeeds.forEach(System.out::println);

		Map<Rssfeed, List<Article>> content = new HashMap<>();
		Stream<Rssfeed> rssfeedsStream = rssfeeds.stream();
		// rssfeedsStream.forEach(rssfeed -> {
		// try {
		// content.put(rssfeed,
		// articlesRetriever.retrieveInformations(rssfeed.getRssUrl()));
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }
		// });

		Function<Rssfeed, Stream<Article>> flatmapper = rssfeed -> {
			try {
				return articlesRetriever.retrieveInformations(rssfeed.getRssUrl()).stream()
						.peek(article -> article.setComments(rssfeed.getLastArticleDate())).peek(System.out::println);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			System.out.println("NULL!");
			return null;
		};
		rssfeedsStream.flatMap(flatmapper).forEach(article -> System.out.println(article.toString()));
	}

}
