package slackbot.feed.utils.mappers;

import slackbot.feed.utils.channel.Channel;

public interface ChannelMapper<T> {

	Channel toChannel(T t);

}
