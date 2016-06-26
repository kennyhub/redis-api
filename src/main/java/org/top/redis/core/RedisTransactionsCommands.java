package org.top.redis.core;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisTransactionsCommands {

    String watch(String... keys);

    String unwatch();
}
