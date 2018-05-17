package slackbot.bot;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import slackbot.feed.utils.mappers.SlackMessageMapper;
import slackbot.feed.utils.news.Article;
import slackbot.feed.utils.news.SlackMessage;

public class SlackMapperTest {

	private SlackMessageMapper<Article> slackMapper;

	private Article article;

	private String title = "SlackMessage title test";
	private String link = "SlackMessage link test";
	private String description = "SlackMessage description for test purpose";
	private String category = "#SlackMessage category test";

	@Before
	public void init() {
		slackMapper = new SlackMapper();

		article = new Article();
		article.setTitle(title);
		article.setLink(link);
		article.setDescription(description);
		article.setCategory(category);
	}

	@Test
	public void toSlackMessageTest() {
		SlackMessage slackMessage = slackMapper.toSlackMessage(article);
		// TODO faire mieux
		if (slackMessage.getDescription().contains(link)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		if (slackMessage.getLink().startsWith("#")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

	}

	@Test(expected = NullPointerException.class)
	public void toSlackMessageNullTest() {
		slackMapper.toSlackMessage(null);
	}

	@Test(expected = NullPointerException.class)
	public void toSlackMessageArticleTitleNullTest() {
		article.setTitle(null);
		article.setDescription(description);
		article.setLink(link);
		article.setCategory(category);

		slackMapper.toSlackMessage(article);
	}

	@Test(expected = NullPointerException.class)
	public void toSlackMessageArticleLinkNullTest() {
		article.setTitle(title);
		article.setDescription(description);
		article.setLink(null);
		article.setCategory(category);

		slackMapper.toSlackMessage(article);
	}

	@Test(expected = NullPointerException.class)
	public void toSlackMessageArticleDescriptionNullTest() {
		article.setTitle(title);
		article.setDescription(null);
		article.setLink(link);
		article.setCategory(category);

		slackMapper.toSlackMessage(article);
	}

	@Test(expected = NullPointerException.class)
	public void toSlackMessageArticleCategoryNullTest() {
		article.setTitle(title);
		article.setDescription(description);
		article.setLink(link);
		article.setCategory(null);

		slackMapper.toSlackMessage(article);
	}
}
