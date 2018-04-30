package slackbot.data.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import slackbot.data.jpa.model.Webhook;
import slackbot.data.jpa.repositories.WebhookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WebhookRepositoryIntegrationTest {

	@Autowired
	private WebhookRepository webhookRepository;

	private Webhook webhookTest;

	@Before
	public void init() {
		webhookTest = new Webhook();
		webhookTest.setTitle("webhook_test");
		webhookTest.setUrl("url_test");

	}

	@Test
	public void testfindAll() {
		List<Webhook> webhooks = webhookRepository.findAll();
		assertThat(webhooks.size(), is(greaterThanOrEqualTo(0)));
	}

	@Test
	public void testcreate_find_deletion() {
		List<Webhook> webhooks = webhookRepository.findAll();
		int webhookCount = webhooks.size();
		assertThat(webhookCount, is(greaterThanOrEqualTo(0)));

		webhookTest = webhookRepository.save(webhookTest);
		webhooks = webhookRepository.findAll();
		assertEquals(webhookCount + 1, webhooks.size());

		Webhook webhookInDatabase = webhookRepository.findById(webhookTest.getId()).get();
		assertNotNull(webhookInDatabase);
		assertEquals(webhookTest.getTitle(), webhookInDatabase.getTitle());
		assertEquals(webhookTest.getUrl(), webhookInDatabase.getUrl());

		webhookRepository.delete(webhookInDatabase);
		webhooks = webhookRepository.findAll();
		assertThat(webhookCount, is(greaterThanOrEqualTo(0)));
		assertEquals(webhookCount, webhooks.size());

		boolean isFind = webhookRepository.findById(webhookInDatabase.getId()).isPresent();
		assertFalse(isFind);
	}

}
