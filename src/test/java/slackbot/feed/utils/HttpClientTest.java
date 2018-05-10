package slackbot.feed.utils;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HttpClientTest {

	@Autowired
	private slackbot.feed.utils.HttpClient httpClient;

	private String url;

	@Before
	public void init() {
		url = "http://www.google.com";
	}

	@Test
	public void testGet() {
		Map<String, String> getResult = httpClient.get(url);
		assertEquals(getResult.get(httpClient.RETURN_CODE), "200");
	}

	@Test
	public void testPost() {

	}
}
