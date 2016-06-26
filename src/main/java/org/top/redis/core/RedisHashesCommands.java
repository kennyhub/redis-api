package org.top.redis.core;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisHashesCommands {
    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hsetnx(String key, String field, String value);

    String hmset(String key, Map<String, String> hash);

    List<String> hmget(String key, String... fields);

    Long hincrBy(String key, String field, long value);

    Double hincrByFloat(final String key, final String field, final double value);

    Boolean hexists(String key, String field);

    Long hdel(String key, String... field);

    Long hlen(String key);

    Set<String> hkeys(String key);

    List<String> hvals(String key);

    Map<String, String> hgetAll(String key);

    ScanResult<Map.Entry<String, String>> hscan(final String key, final String cursor);

    ScanResult<Map.Entry<String, String>> hscan(final String key, final String cursor,
                                                final ScanParams params);
}
