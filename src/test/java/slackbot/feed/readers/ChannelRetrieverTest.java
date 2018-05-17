package slackbot.feed.readers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import slackbot.feed.utils.HttpClient;
import slackbot.feed.utils.news.Article;
import slackbot.feed.utils.xmlparser.Channel;
import slackbot.feed.utils.xmlparser.XMLParser;

public class ChannelRetrieverTest {

	@InjectMocks
	ChannelRetriever channelRetriever;

	@Mock
	private HttpClient httpClient;

	@Mock
	private XMLParser<Channel> xmlParser;

	private String titleChannel = "titleChannelTest";
	private String descriptionChannel = "Channel description for test purpose";
	private String linkChannel = "Channel url";
	private Channel channel;

	private String titleArticle = "titleArticleTest";
	private String descriptionArticle = "Article description for test";
	private String linkArticle = "Article url";
	private String author = "The dev guy";
	private List<Article> articles;

	private Map<String, String> httpClient_result;
	private String returnCode = "200";
	private String returnPayload = "payload";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		Article article = new Article();
		article.setTitle(titleArticle);
		article.setDescription(descriptionArticle);
		article.setLink(linkArticle);
		article.setAuthor(author);

		articles = new ArrayList<>();
		articles.add(article);

		channel = new Channel();
		channel.setTitle(titleChannel);
		channel.setDescription(descriptionChannel);
		channel.setLink(linkChannel);
		channel.setArticles(articles);

		httpClient_result = new HashMap<>();
		httpClient_result.put(HttpClient.RETURN_CODE, returnCode);
		httpClient_result.put(HttpClient.RETURN_PAYLOAD, returnPayload);

	}

	@Test
	public void retrieveInformationsTest() {
		try {
			when(httpClient.get(linkChannel)).thenReturn(httpClient_result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		when(xmlParser.parse(returnPayload)).thenReturn(channel);

		try {
			articles = channelRetriever.retrieveInformations(linkChannel);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		assertEquals(articles.size(), channel.getArticles().size());
		assertEquals(articles.get(0).getTitle(), titleArticle);
	}

	@Test(expected = NullPointerException.class)
	public void retrieveInformationsTestNull() {
		try {
			channelRetriever.retrieveInformations(null);
		} catch (MalformedURLException e) {
		}
	}

	// TODO
	// @Test(expected = MalformedURLException.class)
	@Test
	public void retrieveInformationsTestUrlFormat() {// throws MalformedURLException {
		try {
			channelRetriever.retrieveInformations("malformed url for test");
		} catch (MalformedURLException e) {
			assertTrue(true);
		}
	}

}
