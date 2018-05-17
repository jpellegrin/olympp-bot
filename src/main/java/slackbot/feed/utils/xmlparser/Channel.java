package slackbot.feed.utils.xmlparser;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import slackbot.feed.utils.news.Article;

@XmlRootElement
public class Channel {

	private String title;

	private String description;

	private String link;

	private List<Article> articles;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
