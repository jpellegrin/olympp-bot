package slackbot.feed.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import slackbot.feed.utils.xmlparser.XMLParser;

public class XMLParserTest {

	private String name;
	private String url;
	private String xmlString;

	private XMLParser<PojoForTest> xmlParser;

	@Before
	public void init() {
		xmlParser = new XMLParser<PojoForTest>(PojoForTest.class);
		name = "John Doe";
		url = "http://johnDoe.com/url";
		xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + "<pojoForTest>" + "	<name>" + name + "</name>"
				+ "	<url>" + url + "</url>" + "</pojoForTest>";
	}

	@Test(expected = NullPointerException.class)
	public void testParsNull() {
		PojoForTest pojoTest = xmlParser.parse(null);
	}

	@Test
	public void testParse() {
		PojoForTest pojoTest = xmlParser.parse(xmlString);
		assertEquals(name, pojoTest.getName());
		assertEquals(url, pojoTest.getUrl());
	}
}
