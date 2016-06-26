package org.top.redis.core;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisPubSubCommands {

    void psubscribe(final JedisPubSub jedisPubSub, final String... patterns);

    Long publish(final String channel, final String message);

    void subscribe(final JedisPubSub jedisPubSub, final String... channels);

    List<String> pubsubChannels(String pattern);

    Long pubsubNumPat();

    Map<String, String> pubsubNumSub(String... channels);

    String substr(final String key, final int start, final int end);

}
