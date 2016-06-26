package org.top.redis.core;

import redis.clients.jedis.BinaryClient;

import java.util.List;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisListsCommands {

    List<String> blpop(int timeout, String key);

    List<String> brpop(int timeout, String key);

    String brpoplpush(String source, String destination, int timeout);

    String lindex(final String key, final long index);

    Long linsert(final String key, final BinaryClient.LIST_POSITION where, final String pivot,
                 final String value);

    Long llen(final String key);

    String lpop(final String key);

    Long lpush(final String key, final String... strings);

    Long lpushx(final String key, final String... string);

    List<String> lrange(final String key, final long start, final long end);

    Long lrem(final String key, final long count, final String value);

    String lset(final String key, final long index, final String value);

    String ltrim(final String key, final long start, final long end);

    String rpop(final String key);

    String rpoplpush(final String srckey, final String dstkey);

    Long rpush(final String key, final String... strings);

    Long rpushx(final String key, final String... string);
}
