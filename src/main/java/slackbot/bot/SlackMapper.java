package slackbot.bot;

import org.springframework.stereotype.Component;

import slackbot.feed.utils.mappers.SlackMessageMapper;
import slackbot.feed.utils.news.Article;
import slackbot.feed.utils.news.SlackMessage;

@Component
public class SlackMapper implements SlackMessageMapper<Article> {

	public SlackMessage toSlackMessage(Article article) {
		if (article == null) {
			throw new NullPointerException("article is null");
		}
		if (article.getTitle() == null) {
			throw new NullPointerException("title is null");
		}
		if (article.getDescription() == null) {
			throw new NullPointerException("description is null");
		}
		if (article.getLink() == null) {
			throw new NullPointerException("link is null");
		}
		if (article.getCategory() == null) {
			throw new NullPointerException("category is null");
		}

		SlackMessage slackMessage = new SlackMessage();
		slackMessage.setTitle(article.getTitle());
		slackMessage.setLink(article.getCategory());

		String urlBalise = "<" + article.getLink() + "|en savoir plus>";
		slackMessage.setDescription(article.getDescription() + " " + urlBalise);

		return slackMessage;
	}

}
