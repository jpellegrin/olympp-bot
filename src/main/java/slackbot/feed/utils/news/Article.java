package slackbot.feed.utils.news;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class Article extends News {

	private String pubDate;

	private String guid;

	private String author;

	private String category;

	private String comments;

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("article id:" + guid + "\n");
		sb.append("		title :" + getTitle() + "\n");
		sb.append("		description :" + getDescription() + "\n");
		sb.append("		link :" + getLink() + "\n");
		sb.append("		author :" + author + "\n");
		sb.append("		categpry :" + category + "\n");
		sb.append("		comments :" + comments + "\n");
		sb.append("		pubDate :" + pubDate + "\n");
		return sb.toString();
	}

}
