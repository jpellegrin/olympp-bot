package slackbot.webservices.controllers;

import java.time.Instant;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/slackbot/test")
public class IntegrationTestsController {

	@GetMapping("/getRssfeedForTest")
	public @ResponseBody String getRssfedForTest() {

		Instant timestamp = Instant.now();

		StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuilder.append("<rss version=\"2.0\">");
		xmlBuilder.append("    <channel>");
		xmlBuilder.append("        <title>Rssfeed Test</title>");
		xmlBuilder.append("        <description>Sample of an 2.0 RSS feed</description>");
		xmlBuilder.append("        <lastBuildDate>" + timestamp + "</lastBuildDate>");
		xmlBuilder.append("        <link>http://localhost:8080/slackbot/test/getRssfeedForTest</link>");
		xmlBuilder.append("        <item>");
		xmlBuilder.append("            <title>Test news</title>");
		xmlBuilder.append("            <description>Test description</description>");
		Random r = new Random();
		xmlBuilder.append("				<guid>" + r.nextLong() + "</guid>");
		xmlBuilder.append("            <link>http://localhost:8080/slackbot/test/getRssfeedForTest</link>");
		timestamp = Instant.now();
		xmlBuilder.append("            <pubDate>" + timestamp + "</pubDate>");
		xmlBuilder.append("        </item>");
		xmlBuilder.append("    </channel>");
		xmlBuilder.append("</rss>");

		return xmlBuilder.toString();
	}

}
