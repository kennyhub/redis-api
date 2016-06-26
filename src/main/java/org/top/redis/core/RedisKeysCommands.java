package org.top.redis.core;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Set;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisKeysCommands {
    Long del(final String key);

    byte[] dump(final String key);

    Boolean exists(final String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Set<String> keys(final String pattern);

    String migrate(final String host, final int port, final String key,
                   final int destinationDb, final int timeout);

    Long move(final String key, final int dbIndex);

    Long objectRefcount(String string);

    String objectEncoding(String string);

    Long objectIdletime(String string);

    Long persist(final String key);

    Long pexpire(final String key, final long milliseconds);

    Long pexpireAt(final String key, final long millisecondsTimestamp);

    Long pttl(final String key);

    String randomKey();

    String rename(final String oldkey, final String newkey);

    Long renamenx(final String oldkey, final String newkey);

    String restore(final String key, final int ttl, final byte[] serializedValue);

    List<String> sort(final String key);

    List<String> sort(final String key, final SortingParams sortingParameters);

    Long sort(final String key, final SortingParams sortingParameters, final String dstkey);

    Long sort(final String key, final String dstkey);

    Long ttl(final String key);

    String type(final String key);

    Long waitReplicas(int replicas, long timeout);

    ScanResult<String> scan(final String cursor);

    ScanResult<String> scan(final String cursor, final ScanParams params);
}
