package slackbot.bot;

import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import slackbot.data.jpa.model.Rssfeed;
import slackbot.data.jpa.model.Webhook;
import slackbot.data.jpa.repositories.RssfeedRepository;
import slackbot.data.jpa.repositories.WebhookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RssfeedReaderAndSlackPosterTest {

	@Autowired
	private RssfeedReaderAndSlackPoster rssfeedReaderAndSlackPoster;

	@Autowired
	private RssfeedRepository rssfeedRepository;

	@Autowired
	private WebhookRepository webhookRepository;

	// Same name as in database
	private String rssfeedTitleTest = "Rssfeed Test";
	private String rssfeedUrlTest = "http://localhost:8080/slackbot/test/getRssfeedForTest";
	private String slackChannelTest = "mentoring_vincent";
	private String rssfeedLastArticleDateTest = Instant.now().toString();

	private String webhookUrlTest = "https://hooks.slack.com/services/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";

	private Example<Rssfeed> rssfeedExample;

	@Before
	public void init() {
		Rssfeed rssfeed = new Rssfeed();
		rssfeed.setTitle(rssfeedTitleTest);
		rssfeed.setSlackChannels(slackChannelTest);
		rssfeedExample = Example.of(rssfeed);
		Optional<Rssfeed> rssfeedOptional = rssfeedRepository.findOne(rssfeedExample);

		if (rssfeedOptional.isPresent()) {
			rssfeed = rssfeedOptional.get();
			rssfeedLastArticleDateTest = rssfeed.getLastArticleDate();
		} else {
			rssfeed.setTitle(rssfeedTitleTest);
			rssfeed.setLastArticleDate(rssfeedLastArticleDateTest);
			rssfeed.setRssUrl(rssfeedUrlTest);
			rssfeed.setSlackChannels(slackChannelTest);

			rssfeedRepository.save(rssfeed);
		}

		Webhook webhook = new Webhook();
		webhook.setTitle(slackChannelTest);
		Example<Webhook> webhookExample = Example.of(webhook);
		Optional<Webhook> webhookOptional = webhookRepository.findOne(webhookExample);

		if (!webhookOptional.isPresent()) {
			webhook.setTitle(slackChannelTest);
			webhook.setUrl(webhookUrlTest);

			webhookRepository.save(webhook);
		}

	}

	@Test
	public void retrieveFromRssfeedAndSendToSlack() {
		rssfeedReaderAndSlackPoster.retrieveFromRssfeedAndSendToSlack();

		Optional<Rssfeed> rssfeedOptional = rssfeedRepository.findOne(rssfeedExample);
		if (rssfeedOptional.isPresent()) {
			String newRssfeedLastArticleDateTest = rssfeedOptional.get().getLastArticleDate();
			Instant rssfeedLastArticleDateInstatntTest = Instant.parse(rssfeedLastArticleDateTest);
			Instant newRssfeedLastArticleDateInstantTest = Instant.parse(newRssfeedLastArticleDateTest);

			if (rssfeedLastArticleDateInstatntTest.isBefore(newRssfeedLastArticleDateInstantTest)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

		} else {
			assertTrue(false);
		}
	}
}
