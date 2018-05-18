package slackbot.data.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rssfeed {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String title;

	private String imageUrl;

	private String lastArticleDate;

	private String rssUrl;

	private String lastArticleGuid;

	private String slackChannels;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLastArticleDate() {
		return lastArticleDate;
	}

	public void setLastArticleDate(String lastArticleDate) {
		this.lastArticleDate = lastArticleDate;
	}

	public String getRssUrl() {
		return rssUrl;
	}

	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public String getLastArticleGuid() {
		return lastArticleGuid;
	}

	public void setLastArticleGuid(String lastArticleGuid) {
		this.lastArticleGuid = lastArticleGuid;
	}

	public String getSlackChannels() {
		return slackChannels;
	}

	public void setSlackChannels(String slackChannels) {
		this.slackChannels = slackChannels;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Rssfeed id:" + id + "\n");
		sb.append("		title :" + title + "\n");
		sb.append("		rssUrl :" + rssUrl + "\n");
		sb.append("		lastArticleDate :" + lastArticleDate + "\n");
		sb.append("		lastArticleGuid :" + lastArticleGuid + "\n");
		sb.append("		slackChannels :" + slackChannels + "\n");
		return sb.toString();
	}

}
