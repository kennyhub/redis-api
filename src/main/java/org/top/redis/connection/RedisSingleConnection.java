package org.top.redis.connection;

import org.top.redis.core.RedisCommands;
import org.top.redis.pipeline.RedisPipelineCallback;
import org.top.redis.transaction.RedisTransactionCallback;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Pool;
import redis.clients.util.Slowlog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yubin on 16/4/12.
 */
public class RedisSingleConnection implements RedisCommands {

    private Pool<Jedis> pool;

    public RedisSingleConnection(Pool<Jedis> pool) {
        this.pool = pool;
    }

    @Override
    public String clusterNodes() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterNodes();
            }
        }.run();
    }

    @Override
    public String clusterMeet(final String ip, final int port) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterMeet(ip, port);
            }
        }.run();
    }

    @Override
    public String clusterAddSlots(final int... slots) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterAddSlots(slots);
            }
        }.run();
    }

    @Override
    public String clusterDelSlots(final int... slots) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterDelSlots(slots);
            }
        }.run();
    }

    @Override
    public String clusterInfo() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterInfo();
            }
        }.run();
    }

    @Override
    public List<String> clusterGetKeysInSlot(final int slot, final int count) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.clusterGetKeysInSlot(slot, count);
            }
        }.run();
    }

    @Override
    public String clusterSetSlotNode(final int slot, final String nodeId) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterSetSlotNode(slot, nodeId);
            }
        }.run();
    }

    @Override
    public String clusterSetSlotMigrating(final int slot, final String nodeId) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterSetSlotMigrating(slot, nodeId);
            }
        }.run();
    }

    @Override
    public String clusterSetSlotImporting(final int slot, final String nodeId) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterSetSlotImporting(slot, nodeId);
            }
        }.run();
    }

    @Override
    public String clusterSetSlotStable(final int slot) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterSetSlotStable(slot);
            }
        }.run();
    }

    @Override
    public String clusterForget(final String nodeId) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterForget(nodeId);
            }
        }.run();
    }

    @Override
    public String clusterFlushSlots() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterFlushSlots();
            }
        }.run();
    }

    @Override
    public Long clusterKeySlot(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.clusterKeySlot(key);
            }
        }.run();
    }

    @Override
    public Long clusterCountKeysInSlot(final int slot) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.clusterCountKeysInSlot(slot);
            }
        }.run();
    }

    @Override
    public String clusterSaveConfig() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterSaveConfig();
            }
        }.run();
    }

    @Override
    public String clusterReplicate(final String nodeId) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterReplicate(nodeId);
            }
        }.run();
    }

    @Override
    public List<String> clusterSlaves(final String nodeId) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.clusterSlaves(nodeId);
            }
        }.run();
    }

    @Override
    public String clusterFailover() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterFailover();
            }
        }.run();
    }

    @Override
    public List<Object> clusterSlots() {
        return new RedisSingleCommand<List<Object>>(pool) {
            @Override
            public List<Object> execute(Jedis connection) {
                return connection.clusterSlots();
            }
        }.run();
    }

    @Override
    public String clusterReset(final JedisCluster.Reset resetType) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterReset(resetType);
            }
        }.run();
    }

    @Override
    public String readonly() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.readonly();
            }
        }.run();
    }

    @Override
    public String ping() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.ping();
            }
        }.run();
    }

    @Override
    public String quit() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.quit();
            }
        }.run();
    }

    @Override
    public String flushDB() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.flushDB();
            }
        }.run();
    }

    @Override
    public Long dbSize() {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.dbSize();
            }
        }.run();
    }

    @Override
    public String select(final int index) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.select(index);
            }
        }.run();
    }

    @Override
    public String flushAll() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.flushAll();
            }
        }.run();
    }

    @Override
    public String auth(final String password) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.auth(password);
            }
        }.run();
    }

    @Override
    public String save() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.save();
            }
        }.run();
    }

    @Override
    public String bgsave() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.bgsave();
            }
        }.run();
    }

    @Override
    public String bgrewriteaof() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.bgrewriteaof();
            }
        }.run();
    }

    @Override
    public Long lastsave() {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lastsave();
            }
        }.run();
    }

    @Override
    public String shutdown() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.shutdown();
            }
        }.run();
    }

    @Override
    public String info() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.info();
            }
        }.run();
    }

    @Override
    public String info(final String section) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.info(section);
            }
        }.run();
    }

    @Override
    public String slaveof(final String host, final int port) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.slaveof(host, port);
            }
        }.run();
    }

    @Override
    public String slaveofNoOne() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.slaveofNoOne();
            }
        }.run();
    }

    @Override
    public Long getDB() {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.getDB();
            }
        }.run();
    }

    @Override
    public String debug(final DebugParams params) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.debug(params);
            }
        }.run();
    }

    @Override
    public String configResetStat() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.configResetStat();
            }
        }.run();
    }

    @Override
    public List<String> configGet(final String pattern) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.configGet(pattern);
            }
        }.run();
    }

    @Override
    public String configSet(final String parameter, final String value) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.configSet(parameter, value);
            }
        }.run();
    }

    @Override
    public String slowlogReset() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.slowlogReset();
            }
        }.run();
    }

    @Override
    public Long slowlogLen() {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.slowlogLen();
            }
        }.run();
    }

    @Override
    public List<Slowlog> slowlogGet() {
        return new RedisSingleCommand<List<Slowlog>>(pool) {
            @Override
            public List<Slowlog> execute(Jedis connection) {
                return connection.slowlogGet();
            }
        }.run();
    }

    @Override
    public List<Slowlog> slowlogGet(final long entries) {
        return new RedisSingleCommand<List<Slowlog>>(pool) {
            @Override
            public List<Slowlog> execute(Jedis connection) {
                return connection.slowlogGet(entries);
            }
        }.run();
    }

    @Override
    public Long pfadd(final String key, final String... elements) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pfadd(key, elements);
            }
        }.run();
    }

    @Override
    public long pfcount(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pfcount(key);
            }
        }.run();
    }

    @Override
    public Long del(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.del(key);
            }
        }.run();
    }

    @Override
    public byte[] dump(final String key) {
        return new RedisSingleCommand<byte[]>(pool) {
            @Override
            public byte[] execute(Jedis connection) {
                return connection.dump(key);
            }
        }.run();
    }

    @Override
    public Boolean exists(final String key) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.exists(key);
            }
        }.run();
    }

    @Override
    public Long expire(final String key, final int seconds) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.expire(key, seconds);
            }
        }.run();
    }

    @Override
    public Long expireAt(final String key, final long unixTime) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.expireAt(key, unixTime);
            }
        }.run();
    }

    @Override
    public Set<String> keys(final String pattern) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.keys(pattern);
            }
        }.run();
    }

    @Override
    public String migrate(final String host, final int port, final String key, final int destinationDb, final int timeout) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.migrate(host, port, key, destinationDb, timeout);
            }
        }.run();
    }

    @Override
    public Long move(final String key, final int dbIndex) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.move(key, dbIndex);
            }
        }.run();
    }

    @Override
    public Long objectRefcount(final String string) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.objectRefcount(string);
            }
        }.run();
    }

    @Override
    public String objectEncoding(final String string) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.objectEncoding(string);
            }
        }.run();
    }

    @Override
    public Long objectIdletime(final String string) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.objectIdletime(string);
            }
        }.run();
    }

    @Override
    public Long persist(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.persist(key);
            }
        }.run();
    }

    @Override
    public Long pexpire(final String key, final long milliseconds) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pexpire(key, milliseconds);
            }
        }.run();
    }

    @Override
    public Long pexpireAt(final String key, final long millisecondsTimestamp) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pexpireAt(key, millisecondsTimestamp);
            }
        }.run();
    }

    @Override
    public Long pttl(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pttl(key);
            }
        }.run();
    }

    @Override
    public String randomKey() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.randomKey();
            }
        }.run();
    }

    @Override
    public String rename(final String oldkey, final String newkey) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.rename(oldkey, newkey);
            }
        }.run();
    }

    @Override
    public Long renamenx(final String oldkey, final String newkey) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.renamenx(oldkey, newkey);
            }
        }.run();
    }

    @Override
    public String restore(final String key, final int ttl, final byte[] serializedValue) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.restore(key, ttl, serializedValue);
            }
        }.run();
    }

    @Override
    public List<String> sort(final String key) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.sort(key);
            }
        }.run();
    }

    @Override
    public List<String> sort(final String key, final SortingParams sortingParameters) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.sort(key, sortingParameters);
            }
        }.run();
    }

    @Override
    public Long sort(final String key, final SortingParams sortingParameters, final String dstkey) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sort(key, sortingParameters, dstkey);
            }
        }.run();
    }

    @Override
    public Long sort(final String key, final String dstkey) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sort(key, dstkey);
            }
        }.run();
    }

    @Override
    public Long ttl(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.ttl(key);
            }
        }.run();
    }

    @Override
    public String type(final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.type(key);
            }
        }.run();
    }

    @Override
    public Long waitReplicas(final int replicas, final long timeout) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.waitReplicas(replicas, timeout);
            }
        }.run();
    }

    @Override
    public ScanResult<String> scan(final String cursor) {
        return new RedisSingleCommand<ScanResult<String>>(pool) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.scan(cursor);
            }
        }.run();
    }

    @Override
    public ScanResult<String> scan(final String cursor, final ScanParams params) {
        return new RedisSingleCommand<ScanResult<String>>(pool) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.scan(cursor, params);
            }
        }.run();
    }

    @Override
    public List<String> blpop(final int timeout, final String key) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.blpop(timeout, key);
            }
        }.run();
    }

    @Override
    public List<String> brpop(final int timeout, final String key) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.brpop(timeout, key);
            }
        }.run();
    }

    @Override
    public String brpoplpush(final String source, final String destination, final int timeout) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.brpoplpush(source, destination, timeout);
            }
        }.run();
    }

    @Override
    public String lindex(final String key, final long index) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.lindex(key, index);
            }
        }.run();
    }

    @Override
    public Long linsert(final String key, final BinaryClient.LIST_POSITION where, final String pivot, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.linsert(key, where, pivot, value);
            }
        }.run();
    }

    @Override
    public Long llen(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.llen(key);
            }
        }.run();
    }

    @Override
    public String lpop(final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.lpop(key);
            }
        }.run();
    }

    @Override
    public Long lpush(final String key, final String... strings) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lpush(key, strings);
            }
        }.run();
    }

    @Override
    public Long lpushx(final String key, final String... string) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lpushx(key, string);
            }
        }.run();
    }

    @Override
    public List<String> lrange(final String key, final long start, final long end) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.lrange(key, start, end);
            }
        }.run();
    }

    @Override
    public Long lrem(final String key, final long count, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.lrem(key, count, value);
            }
        }.run();
    }

    @Override
    public String lset(final String key, final long index, final String value) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.lset(key, index, value);
            }
        }.run();
    }

    @Override
    public String ltrim(final String key, final long start, final long end) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.ltrim(key, start, end);
            }
        }.run();
    }

    @Override
    public String rpop(final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.rpop(key);
            }
        }.run();
    }

    @Override
    public String rpoplpush(final String srckey, final String dstkey) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.rpoplpush(srckey, dstkey);
            }
        }.run();
    }

    @Override
    public Long rpush(final String key, final String... strings) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.rpush(key, strings);
            }
        }.run();
    }

    @Override
    public Long rpushx(final String key, final String... string) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.rpushx(key, string);
            }
        }.run();
    }

    @Override
    public Long del(final String... keys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.del(keys);
            }
        }.run();
    }

    @Override
    public Long exists(final String... keys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.exists(keys);
            }
        }.run();
    }

    @Override
    public List<String> blpop(final int timeout, final String... keys) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.blpop(timeout, keys);
            }
        }.run();
    }

    @Override
    public List<String> brpop(final int timeout, final String... keys) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.brpop(timeout, keys);
            }
        }.run();
    }

    @Override
    public Long zinterstore(final String dstkey, final String... sets) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zinterstore(dstkey, sets);
            }
        }.run();
    }

    @Override
    public Long zinterstore(final String dstkey, final ZParams params, final String... sets) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zinterstore(dstkey, params, sets);
            }
        }.run();
    }

    @Override
    public String pfmerge(final String destkey, final String... sourcekeys) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.pfmerge(destkey, sourcekeys);
            }
        }.run();
    }

    @Override
    public long pfcount(final String... keys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pfcount(keys);
            }
        }.run();
    }

    @Override
    public void psubscribe(final JedisPubSub jedisPubSub, final String... patterns) {
        new RedisSingleCommand<Integer>(pool) {
            @Override
            public Integer execute(Jedis connection) {
                connection.psubscribe(jedisPubSub, patterns);
                return 0;
            }
        }.run();
    }

    @Override
    public Long publish(final String channel, final String message) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.publish(channel, message);
            }
        }.run();
    }

    @Override
    public void subscribe(final JedisPubSub jedisPubSub, final String... channels) {
        new RedisSingleCommand<Integer>(pool) {
            @Override
            public Integer execute(Jedis connection) {
                connection.subscribe(jedisPubSub, channels);
                return 0;
            }
        }.run();
    }

    @Override
    public List<String> pubsubChannels(final String pattern) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.pubsubChannels(pattern);
            }
        }.run();
    }

    @Override
    public Long pubsubNumPat() {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.pubsubNumPat();
            }
        }.run();
    }

    @Override
    public Map<String, String> pubsubNumSub(final String... channels) {
        return new RedisSingleCommand<Map<String, String>>(pool) {
            @Override
            public Map<String, String> execute(Jedis connection) {
                return connection.pubsubNumSub(channels);
            }
        }.run();
    }

    @Override
    public String substr(final String key, final int start, final int end) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.substr(key, start, end);
            }
        }.run();
    }

    @Override
    public Object eval(final String script, final int keyCount, final String... params) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clusterInfo();
            }
        }.run();
    }

    @Override
    public Object eval(final String script, final List<String> keys, final List<String> args) {
        return new RedisSingleCommand<Object>(pool) {
            @Override
            public Object execute(Jedis connection) {
                return connection.eval(script, keys, args);
            }
        }.run();
    }

    @Override
    public Object eval(final String script) {
        return new RedisSingleCommand<Object>(pool) {
            @Override
            public Object execute(Jedis connection) {
                return connection.eval(script);
            }
        }.run();
    }

    @Override
    public Object evalsha(final String script) {
        return new RedisSingleCommand<Object>(pool) {
            @Override
            public Object execute(Jedis connection) {
                return connection.evalsha(script);
            }
        }.run();
    }

    @Override
    public Object evalsha(final String sha1, final List<String> keys, final List<String> args) {
        return new RedisSingleCommand<Object>(pool) {
            @Override
            public Object execute(Jedis connection) {
                return connection.evalsha(sha1, keys, args);
            }
        }.run();
    }

    @Override
    public Object evalsha(final String sha1, final int keyCount, final String... params) {
        return new RedisSingleCommand<Object>(pool) {
            @Override
            public Object execute(Jedis connection) {
                return connection.evalsha(sha1, keyCount, params);
            }
        }.run();
    }

    @Override
    public Boolean scriptExists(final String sha1, final String key) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.scriptExists(sha1);
            }
        }.run();
    }

    @Override
    public List<Boolean> scriptExists(final String key, final String... sha1) {
        return new RedisSingleCommand<List<Boolean>>(pool) {
            @Override
            public List<Boolean> execute(Jedis connection) {
                return connection.scriptExists(sha1);
            }
        }.run();
    }

    @Override
    public String scriptLoad(final String script, final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.scriptLoad(script);
            }
        }.run();
    }

    @Override
    public Long sadd(final String key, final String... member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sadd(key, member);
            }
        }.run();
    }

    @Override
    public Set<String> smembers(final String key) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.smembers(key);
            }
        }.run();
    }

    @Override
    public Long srem(final String key, final String... member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.srem(key, member);
            }
        }.run();
    }

    @Override
    public String spop(final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.spop(key);
            }
        }.run();
    }

    @Override
    public Set<String> spop(final String key, final long count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.spop(key, count);
            }
        }.run();
    }

    @Override
    public Long scard(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.scard(key);
            }
        }.run();
    }

    @Override
    public Boolean sismember(final String key, final String member) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.sismember(key, member);
            }
        }.run();
    }

    @Override
    public String srandmember(final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.srandmember(key);
            }
        }.run();
    }

    @Override
    public List<String> srandmember(final String key, final int count) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.srandmember(key, count);
            }
        }.run();
    }

    @Override
    public Long strlen(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.strlen(key);
            }
        }.run();
    }

    @Override
    public Set<String> sdiff(final String... keys) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.sdiff(keys);
            }
        }.run();
    }

    @Override
    public Long sdiffstore(final String dstkey, final String... keys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sdiffstore(dstkey, keys);
            }
        }.run();
    }

    @Override
    public Set<String> sinter(final String... keys) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.sinter(keys);
            }
        }.run();
    }

    @Override
    public Long sinterstore(final String dstkey, final String... keys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sinterstore(dstkey, keys);
            }
        }.run();
    }

    @Override
    public Long smove(final String srckey, final String dstkey, final String member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.smove(srckey, dstkey, member);
            }
        }.run();
    }

    @Override
    public Set<String> sunion(final String... keys) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.sunion(keys);
            }
        }.run();
    }

    @Override
    public Long sunionstore(final String dstkey, final String... keys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.sunionstore(dstkey, keys);
            }
        }.run();
    }

    @Override
    public ScanResult<String> sscan(final String key, final String cursor) {
        return new RedisSingleCommand<ScanResult<String>>(pool) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.sscan(key, cursor);
            }
        }.run();
    }

    @Override
    public ScanResult<String> sscan(final String key, final String cursor, final ScanParams params) {
        return new RedisSingleCommand<ScanResult<String>>(pool) {
            @Override
            public ScanResult<String> execute(Jedis connection) {
                return connection.sscan(key, cursor, params);
            }
        }.run();
    }

    @Override
    public Long zadd(final String key, final double score, final String member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, score, member);
            }
        }.run();
    }

    @Override
    public Long zadd(final String key, final double score, final String member, final ZAddParams params) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, score, member, params);
            }
        }.run();
    }

    @Override
    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, scoreMembers);
            }
        }.run();
    }

    @Override
    public Long zadd(final String key, final Map<String, Double> scoreMembers, final ZAddParams params) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zadd(key, scoreMembers, params);
            }
        }.run();
    }

    @Override
    public Set<String> zrange(final String key, final long start, final long end) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrange(key, start, end);
            }
        }.run();
    }

    @Override
    public Long zrem(final String key, final String... member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zrem(key, member);
            }
        }.run();
    }

    @Override
    public Double zincrby(final String key, final double score, final String member) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.zincrby(key, score, member);
            }
        }.run();
    }

    @Override
    public Double zincrby(final String key, final double score, final String member, final ZIncrByParams params) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.zincrby(key, score, member, params);
            }
        }.run();
    }

    @Override
    public Long zrank(final String key, final String member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zrank(key, member);
            }
        }.run();
    }

    @Override
    public Long zrevrank(final String key, final String member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zrevrank(key, member);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrange(final String key, final long start, final long end) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrange(key, start, end);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeWithScores(key, start, end);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeWithScores(key, start, end);
            }
        }.run();
    }

    @Override
    public Long zcard(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zcard(key);
            }
        }.run();
    }

    @Override
    public Double zscore(final String key, final String member) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.zscore(key, member);
            }
        }.run();
    }

    @Override
    public Long zcount(final String key, final double min, final double max) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zcount(key, min, max);
            }
        }.run();
    }

    @Override
    public Long zcount(final String key, final String min, final String max) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zcount(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, max, min);
            }
        }.run();
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max, offset, count);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, max, min);
            }
        }.run();
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByScore(key, min, max, offset, count);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, max, min, offset, count);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, max, min);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset, final int count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByScore(key, max, min, offset, count);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, max, min);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        }.run();
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset, final int count) {
        return new RedisSingleCommand<Set<Tuple>>(pool) {
            @Override
            public Set<Tuple> execute(Jedis connection) {
                return connection.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        }.run();
    }

    @Override
    public Long zremrangeByRank(final String key, final long start, final long end) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByRank(key, start, end);
            }
        }.run();
    }

    @Override
    public Long zremrangeByScore(final String key, final double start, final double end) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByScore(key, start, end);
            }
        }.run();
    }

    @Override
    public Long zremrangeByScore(final String key, final String start, final String end) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByScore(key, start, end);
            }
        }.run();
    }

    @Override
    public Long zlexcount(final String key, final String min, final String max) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zlexcount(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<String> zrangeByLex(final String key, final String min, final String max) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByLex(key, min, max);
            }
        }.run();
    }

    @Override
    public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrangeByLex(key, min, max, offset, count);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrangeByLex(final String key, final String max, final String min) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByLex(key, max, min);
            }
        }.run();
    }

    @Override
    public Set<String> zrevrangeByLex(final String key, final String max, final String min, final int offset, final int count) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.zrevrangeByLex(key, max, min, offset, count);
            }
        }.run();
    }

    @Override
    public Long zremrangeByLex(final String key, final String min, final String max) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zremrangeByLex(key, min, max);
            }
        }.run();
    }

    @Override
    public Long zunionstore(final String dstkey, final String... sets) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zunionstore(dstkey, sets);
            }
        }.run();
    }

    @Override
    public ScanResult<Tuple> zscan(final String key, final String cursor) {
        return new RedisSingleCommand<ScanResult<Tuple>>(pool) {
            @Override
            public ScanResult<Tuple> execute(Jedis connection) {
                return connection.zscan(key, cursor);
            }
        }.run();
    }

    @Override
    public ScanResult<Tuple> zscan(final String key, final String cursor, final ScanParams params) {
        return new RedisSingleCommand<ScanResult<Tuple>>(pool) {
            @Override
            public ScanResult<Tuple> execute(Jedis connection) {
                return connection.zscan(key, cursor, params);
            }
        }.run();
    }

    @Override
    public Long zunionstore(final String dstkey, final ZParams params, final String... sets) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.zunionstore(dstkey, params, sets);
            }
        }.run();
    }

    @Override
    public Long append(final String key, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.append(key, value);
            }
        }.run();
    }

    @Override
    public Long bitcount(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitcount(key);
            }
        }.run();
    }

    @Override
    public Long bitcount(final String key, final long start, final long end) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitcount(key, start, end);
            }
        }.run();
    }

    @Override
    public Long bitop(final BitOP op, final String destKey, final String... srcKeys) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitop(op, destKey, srcKeys);
            }
        }.run();
    }

    @Override
    public Long bitpos(final String key, final boolean value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitpos(key, value);
            }
        }.run();
    }

    @Override
    public Long bitpos(final String key, final boolean value, final BitPosParams params) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.bitpos(key, value, params);
            }
        }.run();
    }

    @Override
    public Long decr(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.decr(key);
            }
        }.run();
    }

    @Override
    public Long decrBy(final String key, final long integer) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.decrBy(key, integer);
            }
        }.run();
    }

    @Override
    public Long incrBy(final String key, final long integer) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.incrBy(key, integer);
            }
        }.run();
    }

    @Override
    public Double incrByFloat(final String key, final double value) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.incrByFloat(key, value);
            }
        }.run();
    }

    @Override
    public Long incr(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.incr(key);
            }
        }.run();
    }

    @Override
    public String get(final String key) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.get(key);
            }
        }.run();
    }

    @Override
    public Boolean getbit(final String key, final long offset) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.getbit(key, offset);
            }
        }.run();
    }

    @Override
    public String getrange(final String key, final long startOffset, final long endOffset) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.getrange(key, startOffset, endOffset);
            }
        }.run();
    }

    @Override
    public String getSet(final String key, final String value) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.getSet(key, value);
            }
        }.run();
    }

    @Override
    public List<String> mget(final String... keys) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.mget(keys);
            }
        }.run();
    }

    @Override
    public String mset(final String... keysvalues) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.mset(keysvalues);
            }
        }.run();
    }

    @Override
    public Long msetnx(final String... keysvalues) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.msetnx(keysvalues);
            }
        }.run();
    }

    @Override
    public String psetex(final String key, final long milliseconds, final String value) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.psetex(key, milliseconds, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final String value) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.set(key, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final String value, final Boolean isNx, final long pxMillSeconds) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                final String NX = (isNx != null && isNx) ? "NX" : "";
                final String PX = "PX";
                return connection.set(key, value, NX, PX, pxMillSeconds);
            }
        }.run();
    }

    @Override
    public Boolean setbit(final String key, final long offset, final boolean value) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.setbit(key, offset, value);
            }
        }.run();
    }

    @Override
    public Boolean setbit(final String key, final long offset, final String value) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.setbit(key, offset, value);
            }
        }.run();
    }

    @Override
    public String setex(final String key, final int seconds, final String value) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.setex(key, seconds, value);
            }
        }.run();
    }

    @Override
    public Long setnx(final String key, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.setnx(key, value);
            }
        }.run();
    }

    @Override
    public Long setrange(final String key, final long offset, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.setrange(key, offset, value);
            }
        }.run();
    }

    @Override
    public String watch(final String... keys) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.watch(keys);
            }
        }.run();
    }

    @Override
    public String unwatch() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.unwatch();
            }
        }.run();
    }

    @Override
    public String clientSetname(final String name) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clientSetname(name);
            }
        }.run();
    }

    @Override
    public String clientGetname() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clientGetname();
            }
        }.run();
    }

    @Override
    public String clientKill(final String client) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clientKill(client);
            }
        }.run();
    }

    @Override
    public String clientList() {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.clientList();
            }
        }.run();
    }

    @Override
    public List<Object> pipelined(final RedisPipelineCallback callback) {
        return new RedisSingleCommand<List<Object>>(pool) {
            @Override
            public List<Object> execute(Jedis connection) {
                return callback.run(connection);
            }
        }.run();
    }

    @Override
    public List<Object> multi(final RedisTransactionCallback callback) {
        return new RedisSingleCommand<List<Object>>(pool) {
            @Override
            public List<Object> execute(Jedis connection) {
                return callback.run(connection);
            }
        }.run();
    }

    @Override
    public Long geoadd(final String key, final double longitude, final double latitude, final String member) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.geoadd(key, longitude, latitude, member);
            }
        }.run();
    }

    @Override
    public Long geoadd(final String key, final Map<String, GeoCoordinate> memberCoordinateMap) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.geoadd(key, memberCoordinateMap);
            }
        }.run();
    }

    @Override
    public Double geodist(final String key, final String member1, final String member2) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.geodist(key, member1, member2);
            }
        }.run();
    }

    @Override
    public Double geodist(final String key, final String member1, final String member2, final GeoUnit unit) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.geodist(key, member1, member2, unit);
            }
        }.run();
    }

    @Override
    public List<String> geohash(final String key, final String... members) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.geohash(key, members);
            }
        }.run();
    }

    @Override
    public List<GeoCoordinate> geopos(final String key, final String... members) {
        return new RedisSingleCommand<List<GeoCoordinate>>(pool) {
            @Override
            public List<GeoCoordinate> execute(Jedis connection) {
                return connection.geopos(key, members);
            }
        }.run();
    }

    @Override
    public List<GeoRadiusResponse> georadius(final String key, final double longitude, final double latitude, final double radius, final GeoUnit unit) {
        return new RedisSingleCommand<List<GeoRadiusResponse>>(pool) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadius(key, longitude, latitude, radius, unit);
            }
        }.run();
    }

    @Override
    public List<GeoRadiusResponse> georadius(final String key, final double longitude, final double latitude, final double radius, final GeoUnit unit, final GeoRadiusParam param) {
        return new RedisSingleCommand<List<GeoRadiusResponse>>(pool) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadius(key, longitude, latitude, radius, unit, param);
            }
        }.run();
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(final String key, final String member, final double radius, final GeoUnit unit) {
        return new RedisSingleCommand<List<GeoRadiusResponse>>(pool) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadiusByMember(key, member, radius, unit);
            }
        }.run();
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(final String key, final String member, final double radius, final GeoUnit unit, final GeoRadiusParam param) {
        return new RedisSingleCommand<List<GeoRadiusResponse>>(pool) {
            @Override
            public List<GeoRadiusResponse> execute(Jedis connection) {
                return connection.georadiusByMember(key, member, radius, unit, param);
            }
        }.run();
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hset(key, field, value);
            }
        }.run();
    }

    @Override
    public String hget(final String key, final String field) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.hget(key, field);
            }
        }.run();
    }

    @Override
    public Long hsetnx(final String key, final String field, final String value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hsetnx(key, field, value);
            }
        }.run();
    }

    @Override
    public String hmset(final String key, final Map<String, String> hash) {
        return new RedisSingleCommand<String>(pool) {
            @Override
            public String execute(Jedis connection) {
                return connection.hmset(key, hash);
            }
        }.run();
    }

    @Override
    public List<String> hmget(final String key, final String... fields) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.hmget(key, fields);
            }
        }.run();
    }

    @Override
    public Long hincrBy(final String key, final String field, final long value) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hincrBy(key, field, value);
            }
        }.run();
    }

    @Override
    public Double hincrByFloat(final String key, final String field, final double value) {
        return new RedisSingleCommand<Double>(pool) {
            @Override
            public Double execute(Jedis connection) {
                return connection.hincrByFloat(key, field, value);
            }
        }.run();
    }

    @Override
    public Boolean hexists(final String key, final String field) {
        return new RedisSingleCommand<Boolean>(pool) {
            @Override
            public Boolean execute(Jedis connection) {
                return connection.hexists(key, field);
            }
        }.run();
    }

    @Override
    public Long hdel(final String key, final String... field) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hdel(key, field);
            }
        }.run();
    }

    @Override
    public Long hlen(final String key) {
        return new RedisSingleCommand<Long>(pool) {
            @Override
            public Long execute(Jedis connection) {
                return connection.hlen(key);
            }
        }.run();
    }

    @Override
    public Set<String> hkeys(final String key) {
        return new RedisSingleCommand<Set<String>>(pool) {
            @Override
            public Set<String> execute(Jedis connection) {
                return connection.hkeys(key);
            }
        }.run();
    }

    @Override
    public List<String> hvals(final String key) {
        return new RedisSingleCommand<List<String>>(pool) {
            @Override
            public List<String> execute(Jedis connection) {
                return connection.hvals(key);
            }
        }.run();
    }

    @Override
    public Map<String, String> hgetAll(final String key) {
        return new RedisSingleCommand<Map<String, String>>(pool) {
            @Override
            public Map<String, String> execute(Jedis connection) {
                return connection.hgetAll(key);
            }
        }.run();
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(final String key, final String cursor) {
        return new RedisSingleCommand<ScanResult<Map.Entry<String, String>>>(pool) {
            @Override
            public ScanResult<Map.Entry<String, String>> execute(Jedis connection) {
                return connection.hscan(key, cursor);
            }
        }.run();
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(final String key, final String cursor, final ScanParams params) {
        return new RedisSingleCommand<ScanResult<Map.Entry<String, String>>>(pool) {
            @Override
            public ScanResult<Map.Entry<String, String>> execute(Jedis connection) {
                return connection.hscan(key, cursor, params);
            }
        }.run();
    }
}
