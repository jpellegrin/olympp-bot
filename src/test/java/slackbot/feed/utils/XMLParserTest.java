package slackbot.feed.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class XMLParserTest {

	private String name = "John Doe";
	private String url = "http://johnDoe.com/url";
	private String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + "<pojoForTest>" + "	<name>" + name
			+ "</name>" + "	<url>" + url + "</url>" + "</pojoForTest>";

	// @Autowired
	private XMLParser<PojoForTest> xmlParser;

	@Before
	public void init() {
		xmlParser = new XMLParser<PojoForTest>(PojoForTest.class);
	}

	@Test
	public void testParse() {
		PojoForTest pojoTest = xmlParser.parse(xmlString);
		assertEquals(name, pojoTest.getName());
		assertEquals(url, pojoTest.getUrl());
	}
}
