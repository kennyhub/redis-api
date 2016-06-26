package org.top.redis.core;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisHyperLogLogCommands {

    String pfmerge(String destinationKey, String... sourceKeys);

    Long pfadd(final String key, final String... elements);

    long pfcount(final String key);
}
