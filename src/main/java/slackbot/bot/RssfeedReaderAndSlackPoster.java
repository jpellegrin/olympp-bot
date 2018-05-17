package slackbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
	private void retrieveFromRssfeedAndSendToSlack() {

	}

}
