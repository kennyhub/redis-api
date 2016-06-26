package org.top.redis.core;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;

import java.util.List;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisStringsCommands {
    Long append(final String key, final String value);

    Long bitcount(final String key);

    Long bitcount(final String key, long start, long end);

    Long bitop(BitOP op, final String destKey, String... srcKeys);

    Long bitpos(final String key, final boolean value);

    Long bitpos(final String key, final boolean value, final BitPosParams params);

    Long decr(final String key);

    Long decrBy(final String key, final long integer);

    Long incrBy(final String key, final long integer);

    Double incrByFloat(final String key, final double value);

    Long incr(final String key);

    String get(final String key);

    Boolean getbit(String key, long offset);

    String getrange(String key, long startOffset, long endOffset);

    String getSet(final String key, final String value);

    List<String> mget(final String... keys);

    String mset(final String... keysvalues);

    Long msetnx(final String... keysvalues);

    String psetex(final String key, final long milliseconds, final String value);

    String set(final String key, String value);

    String set(final String key, String value, Boolean isNx, long pxMillSeconds);

    Boolean setbit(String key, long offset, boolean value);

    Boolean setbit(String key, long offset, String value);

    String setex(final String key, final int seconds, final String value);

    Long setnx(final String key, final String value);

    Long setrange(String key, long offset, String value);

    Long strlen(final String key);
}
