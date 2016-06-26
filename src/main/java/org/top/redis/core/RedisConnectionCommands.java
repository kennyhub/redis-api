package org.top.redis.core;

import org.top.redis.pipeline.RedisPipelineCallback;
import org.top.redis.transaction.RedisTransactionCallback;
import redis.clients.jedis.DebugParams;
import redis.clients.util.Slowlog;

import java.util.List;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisConnectionCommands {
    String ping();

    String quit();

    String flushDB();

    Long dbSize();

    String select(int index);

    String flushAll();

    String auth(String password);

    String save();

    String bgsave();

    String bgrewriteaof();

    Long lastsave();

    String shutdown();

    String info();

    String info(String section);

    String slaveof(String host, int port);

    String slaveofNoOne();

    Long getDB();

    String debug(DebugParams params);

    String configResetStat();

    Long waitReplicas(int replicas, long timeout);

    List<String> configGet(String pattern);

    String configSet(String parameter, String value);

    String slowlogReset();

    Long slowlogLen();

    List<Slowlog> slowlogGet();

    List<Slowlog> slowlogGet(long entries);

    Long objectRefcount(String string);

    String objectEncoding(String string);

    Long objectIdletime(String string);

    String clientSetname(String name);

    String clientGetname();

    String clientKill(String client);

    String clientList();

    List<Object> pipelined(final RedisPipelineCallback callback);

    List<Object> multi(final RedisTransactionCallback callback);
}
