package org.top.redis.core;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Set;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisSetsCommands {

    Long sadd(String key, String... member);

    Set<String> smembers(String key);

    Long srem(String key, String... member);

    String spop(String key);

    Set<String> spop(String key, long count);

    Long scard(String key);

    Boolean sismember(String key, String member);

    String srandmember(String key);

    List<String> srandmember(String key, int count);

    Long strlen(String key);

    Set<String> sdiff(final String... keys);

    Long sdiffstore(final String dstkey, final String... keys);

    Set<String> sinter(final String... keys);

    Long sinterstore(final String dstkey, final String... keys);

    Long smove(final String srckey, final String dstkey, final String member);

    Set<String> sunion(final String... keys);

    Long sunionstore(final String dstkey, final String... keys);

    ScanResult<String> sscan(final String key, final String cursor);

    ScanResult<String> sscan(final String key, final String cursor, final ScanParams params);
}
