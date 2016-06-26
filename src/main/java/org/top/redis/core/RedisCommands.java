package org.top.redis.core;


/**
 * Created by yubin on 16/3/25.
 */
public interface RedisCommands extends RedisHyperLogLogCommands, RedisKeysCommands, RedisListsCommands, RedisPubSubCommands, RedisScriptingCommands, RedisSetsCommands, RedisSortedSetsCommands, RedisStringsCommands, RedisTransactionsCommands, RedisClusterCommands, RedisConnectionCommands, RedisMultiKeyCommands, RedisHashesCommands, RedisGeoCommands {
}
