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

import slackbot.data.jpa.model.Rssfeed;
import slackbot.data.jpa.repositories.RssfeedRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RssfeedRepositoryIntegrationTest {

	@Autowired
	private RssfeedRepository rssfeedRepository;

	private Rssfeed rssfeedTest;

	@Before
	public void init() {
		rssfeedTest = new Rssfeed();
		rssfeedTest.setTitle("rssfeed_test");
		rssfeedTest.setImageUrl("url_image_test");
		rssfeedTest.setLastArticleDate("20170101");
		rssfeedTest.setLastArticleGuid("154");
		rssfeedTest.setRssUrl("url_test");
		rssfeedTest.setSlackChannels("channel_test");

	}

	@Test
	public void testfindAll() {
		List<Rssfeed> rssfeeds = rssfeedRepository.findAll();
		assertThat(rssfeeds.size(), is(greaterThanOrEqualTo(0)));
	}

	@Test
	public void testcreate_find_deletion() {
		List<Rssfeed> rssfeeds = rssfeedRepository.findAll();
		int rssfeedCount = rssfeeds.size();
		assertThat(rssfeedCount, is(greaterThanOrEqualTo(0)));

		rssfeedTest = rssfeedRepository.save(rssfeedTest);
		rssfeeds = rssfeedRepository.findAll();
		assertEquals(rssfeedCount + 1, rssfeeds.size());

		Rssfeed rssfeedInDatabase = rssfeedRepository.findById(rssfeedTest.getId()).get();
		assertNotNull(rssfeedInDatabase);
		assertEquals(rssfeedTest.getImageUrl(), rssfeedInDatabase.getImageUrl());
		assertEquals(rssfeedTest.getRssUrl(), rssfeedInDatabase.getRssUrl());

		rssfeedRepository.delete(rssfeedInDatabase);
		rssfeeds = rssfeedRepository.findAll();
		assertThat(rssfeedCount, is(greaterThanOrEqualTo(0)));
		assertEquals(rssfeedCount, rssfeeds.size());

		boolean isFind = rssfeedRepository.findById(rssfeedInDatabase.getId()).isPresent();
		assertFalse(isFind);
	}

}
