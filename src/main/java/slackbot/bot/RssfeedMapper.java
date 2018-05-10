package slackbot.bot;

import slackbot.data.jpa.model.Rssfeed;
import slackbot.feed.utils.channel.Channel;
import slackbot.feed.utils.mappers.ChannelMapper;

public class RssfeedMapper implements ChannelMapper<Rssfeed> {

	public Channel toChannel(Rssfeed rssfeed) {
		return null;
	}

}
