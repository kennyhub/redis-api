package org.top.redis.connection;

import org.top.redis.core.RedisCommands;
import org.top.redis.pipeline.RedisPipelineCallback;
import org.top.redis.transaction.RedisTransactionCallback;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Slowlog;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yubin on 16/4/12.
 */
public class RedisClusterConnection implements RedisCommands {

    private JedisCluster jedisCluster;

    public RedisClusterConnection(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public String clusterNodes() {
        return null;
    }

    @Override
    public String clusterMeet(final String ip, final int port) {
        return null;
    }

    @Override
    public String clusterAddSlots(final int... slots) {
        return null;
    }

    @Override
    public String clusterDelSlots(final int... slots) {
        return null;
    }

    @Override
    public String clusterInfo() {
        return null;
    }

    @Override
    public List<String> clusterGetKeysInSlot(final int slot, final int count) {
        return Collections.emptyList();
    }

    @Override
    public String clusterSetSlotNode(final int slot, final String nodeId) {
        return null;
    }

    @Override
    public String clusterSetSlotMigrating(final int slot, final String nodeId) {
        return null;
    }

    @Override
    public String clusterSetSlotImporting(final int slot, final String nodeId) {
        return null;
    }

    @Override
    public String clusterSetSlotStable(final int slot) {
        return null;
    }

    @Override
    public String clusterForget(final String nodeId) {
        return null;
    }

    @Override
    public String clusterFlushSlots() {
        return null;
    }

    @Override
    public Long clusterKeySlot(final String key) {
        return null;
    }

    @Override
    public Long clusterCountKeysInSlot(final int slot) {
        return null;
    }

    @Override
    public String clusterSaveConfig() {
        return null;
    }

    @Override
    public String clusterReplicate(final String nodeId) {
        return null;
    }

    @Override
    public List<String> clusterSlaves(final String nodeId) {
        return null;
    }

    @Override
    public String clusterFailover() {
        return null;
    }

    @Override
    public List<Object> clusterSlots() {
        return Collections.emptyList();
    }

    @Override
    public String clusterReset(final JedisCluster.Reset resetType) {
        return null;
    }

    @Override
    public String readonly() {
        return null;
    }

    @Override
    @Deprecated
    public String ping() {
        return jedisCluster.ping();
    }

    @Override
    @Deprecated
    public String quit() {
        return jedisCluster.quit();
    }

    @Override
    @Deprecated
    public String flushDB() {
        return jedisCluster.flushDB();
    }

    @Override
    @Deprecated
    public Long dbSize() {
        return jedisCluster.dbSize();
    }

    @Override
    @Deprecated
    public String select(final int index) {
        return jedisCluster.select(index);
    }

    @Override
    @Deprecated
    public String flushAll() {
        return jedisCluster.flushAll();
    }

    @Override
    @Deprecated
    public String auth(final String password) {
        return jedisCluster.auth(password);
    }

    @Override
    @Deprecated
    public String save() {
        return jedisCluster.save();
    }

    @Override
    @Deprecated
    public String bgsave() {
        return jedisCluster.bgsave();
    }

    @Override
    @Deprecated
    public String bgrewriteaof() {
        return jedisCluster.bgrewriteaof();
    }

    @Override
    @Deprecated
    public Long lastsave() {
        return jedisCluster.lastsave();
    }

    @Override
    @Deprecated
    public String shutdown() {
        return jedisCluster.shutdown();
    }

    @Override
    @Deprecated
    public String info() {
        return jedisCluster.info();
    }

    @Override
    @Deprecated
    public String info(final String section) {
        return jedisCluster.info(section);
    }

    @Override
    @Deprecated
    public String slaveof(final String host, final int port) {
        return jedisCluster.slaveof(host, port);
    }

    @Override
    @Deprecated
    public String slaveofNoOne() {
        return jedisCluster.slaveofNoOne();
    }

    @Override
    @Deprecated
    public Long getDB() {
        return jedisCluster.getDB();
    }

    @Override
    @Deprecated
    public String debug(final DebugParams params) {
        return jedisCluster.debug(params);
    }

    @Override
    @Deprecated
    public String configResetStat() {
        return jedisCluster.configResetStat();
    }

    @Override
    public List<String> configGet(final String pattern) {
        return Collections.emptyList();
    }

    @Override
    public String configSet(final String parameter, final String value) {
        return null;
    }

    @Override
    public String slowlogReset() {
        return null;
    }

    @Override
    public Long slowlogLen() {
        return null;
    }

    @Override
    public List<Slowlog> slowlogGet() {
        return null;
    }

    @Override
    public List<Slowlog> slowlogGet(final long entries) {
        return null;
    }

    @Override
    public Long pfadd(final String key, final String... elements) {
        return jedisCluster.pfadd(key, elements);
    }

    @Override
    public long pfcount(final String key) {
        return jedisCluster.pfcount(key);
    }

    @Override
    public Long del(final String key) {
        return jedisCluster.del(key);
    }

    @Override
    public byte[] dump(final String key) {
        return null;
    }

    @Override
    public Boolean exists(final String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long expire(final String key, final int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long expireAt(final String key, final long unixTime) {
        return jedisCluster.expireAt(key, unixTime);
    }

    @Override
    public Set<String> keys(final String pattern) {
        return null;
    }

    @Override
    public String migrate(final String host, final int port, final String key, final int destinationDb, final int timeout) {
        return null;
    }

    @Override
    @Deprecated
    public Long move(final String key, final int dbIndex) {
        return jedisCluster.move(key, dbIndex);
    }

    @Override
    public Long objectRefcount(final String string) {
        return null;
    }

    @Override
    public String objectEncoding(final String string) {
        return null;
    }

    @Override
    public Long objectIdletime(final String string) {
        return null;
    }

    @Override
    public Long persist(final String key) {
        return jedisCluster.persist(key);
    }

    @Override
    public Long pexpire(final String key, final long milliseconds) {
        return jedisCluster.pexpire(key, milliseconds);
    }

    @Override
    public Long pexpireAt(final String key, final long millisecondsTimestamp) {
        return jedisCluster.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(final String key) {
        return jedisCluster.pttl(key);
    }

    @Override
    public String randomKey() {
        return null;
    }

    @Override
    public String rename(final String oldkey, final String newkey) {
        return jedisCluster.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(final String oldkey, final String newkey) {
        return jedisCluster.renamenx(oldkey, newkey);
    }

    @Override
    public String restore(final String key, final int ttl, final byte[] serializedValue) {
        return null;
    }

    @Override
    public List<String> sort(final String key) {
        return jedisCluster.sort(key);
    }

    @Override
    public List<String> sort(final String key, final SortingParams sortingParameters) {
        return jedisCluster.sort(key, sortingParameters);
    }

    @Override
    public Long sort(final String key, final SortingParams sortingParameters, final String dstkey) {
        return jedisCluster.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(final String key, final String dstkey) {
        return jedisCluster.sort(key, dstkey);
    }

    @Override
    public Long ttl(final String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public String type(final String key) {
        return jedisCluster.type(key);
    }

    @Override
    @Deprecated
    public Long waitReplicas(final int replicas, final long timeout) {
        return jedisCluster.waitReplicas(replicas, timeout);
    }

    @Override
    public ScanResult<String> scan(final String cursor) {
        return null;
    }

    @Override
    public ScanResult<String> scan(final String cursor, final ScanParams params) {
        return null;
    }

    @Override
    public List<String> blpop(final int timeout, final String key) {
        return jedisCluster.blpop(timeout, key);
    }

    @Override
    public List<String> brpop(final int timeout, final String key) {
        return jedisCluster.brpop(timeout, key);
    }

    @Override
    public String brpoplpush(final String source, final String destination, final int timeout) {
        return jedisCluster.brpoplpush(source, destination, timeout);
    }

    @Override
    public String lindex(final String key, final long index) {
        return jedisCluster.lindex(key, index);
    }

    @Override
    public Long linsert(final String key, final BinaryClient.LIST_POSITION where, final String pivot, final String value) {
        return jedisCluster.linsert(key, where, pivot, value);
    }

    @Override
    public Long llen(final String key) {
        return jedisCluster.llen(key);
    }

    @Override
    public String lpop(final String key) {
        return jedisCluster.lpop(key);
    }

    @Override
    public Long lpush(final String key, final String... strings) {
        return jedisCluster.lpush(key, strings);
    }

    @Override
    public Long lpushx(final String key, final String... string) {
        return jedisCluster.lpushx(key, string);
    }

    @Override
    public List<String> lrange(final String key, final long start, final long end) {
        return jedisCluster.lrange(key, start, end);
    }

    @Override
    public Long lrem(final String key, final long count, final String value) {
        return jedisCluster.lrem(key, count, value);
    }

    @Override
    public String lset(final String key, final long index, final String value) {
        return jedisCluster.lset(key, index, value);
    }

    @Override
    public String ltrim(final String key, final long start, final long end) {
        return jedisCluster.ltrim(key, start, end);
    }

    @Override
    public String rpop(final String key) {
        return jedisCluster.rpop(key);
    }

    @Override
    public String rpoplpush(final String srckey, final String dstkey) {
        return jedisCluster.rpoplpush(srckey, dstkey);
    }

    @Override
    public Long rpush(final String key, final String... strings) {
        return jedisCluster.rpush(key, strings);
    }

    @Override
    public Long rpushx(final String key, final String... string) {
        return jedisCluster.rpushx(key, string);
    }

    @Override
    public Long del(final String... keys) {
        return jedisCluster.del(keys);
    }

    @Override
    public Long exists(final String... keys) {
        return jedisCluster.exists(keys);
    }

    @Override
    public List<String> blpop(final int timeout, final String... keys) {
        return jedisCluster.blpop(timeout, keys);
    }

    @Override
    public List<String> brpop(final int timeout, final String... keys) {
        return jedisCluster.brpop(timeout, keys);
    }

    @Override
    public Long zinterstore(final String dstkey, final String... sets) {
        return jedisCluster.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(final String dstkey, final ZParams params, final String... sets) {
        return jedisCluster.zinterstore(dstkey, params, sets);
    }

    @Override
    public String pfmerge(final String destkey, final String... sourcekeys) {
        return jedisCluster.pfmerge(destkey, sourcekeys);
    }

    @Override
    public long pfcount(final String... keys) {
        return jedisCluster.pfcount(keys);
    }

    @Override
    public void psubscribe(final JedisPubSub jedisPubSub, final String... patterns) {
        jedisCluster.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Long publish(final String channel, final String message) {
        return jedisCluster.publish(channel, message);
    }

    @Override
    public void subscribe(final JedisPubSub jedisPubSub, final String... channels) {
        jedisCluster.subscribe(jedisPubSub, channels);
    }

    @Override
    public List<String> pubsubChannels(final String pattern) {
        return null;
    }

    @Override
    public Long pubsubNumPat() {
        return null;
    }

    @Override
    public Map<String, String> pubsubNumSub(final String... channels) {
        return null;
    }

    @Override
    public String substr(final String key, final int start, final int end) {
        return jedisCluster.substr(key, start, end);
    }

    @Override
    public Object eval(final String script, final int keyCount, final String... params) {
        return jedisCluster.eval(script, keyCount, params);
    }

    @Override
    public Object eval(final String script, final List<String> keys, final List<String> args) {
        return jedisCluster.eval(script, keys, args);
    }

    @Override
    public Object eval(final String script) {
        return jedisCluster.eval(script, 0);
    }

    @Override
    public Object evalsha(final String script) {
        return jedisCluster.evalsha(script, 0);
    }

    @Override
    public Object evalsha(final String sha1, final List<String> keys, final List<String> args) {
        return jedisCluster.evalsha(sha1, keys, args);
    }

    @Override
    public Object evalsha(final String sha1, final int keyCount, final String... params) {
        return jedisCluster.evalsha(sha1, keyCount, params);
    }

    @Override
    public Boolean scriptExists(String sha1, String key) {
        return jedisCluster.scriptExists(sha1, key);
    }

    @Override
    public List<Boolean> scriptExists(String key, String... sha1) {
        return jedisCluster.scriptExists(key, sha1);
    }

    @Override
    public String scriptLoad(String script, String key) {
        return jedisCluster.scriptLoad(script, key);
    }

    @Override
    public Long sadd(final String key, final String... member) {
        return jedisCluster.sadd(key, member);
    }

    @Override
    public Set<String> smembers(final String key) {
        return jedisCluster.smembers(key);
    }

    @Override
    public Long srem(final String key, final String... member) {
        return jedisCluster.srem(key, member);
    }

    @Override
    public String spop(final String key) {
        return jedisCluster.spop(key);
    }

    @Override
    public Set<String> spop(final String key, final long count) {
        return jedisCluster.spop(key, count);
    }

    @Override
    public Long scard(final String key) {
        return jedisCluster.scard(key);
    }

    @Override
    public Boolean sismember(final String key, final String member) {
        return jedisCluster.sismember(key, member);
    }

    @Override
    public String srandmember(final String key) {
        return jedisCluster.srandmember(key);
    }

    @Override
    public List<String> srandmember(final String key, final int count) {
        return jedisCluster.srandmember(key, count);
    }

    @Override
    public Long strlen(final String key) {
        return jedisCluster.strlen(key);
    }

    @Override
    public Set<String> sdiff(final String... keys) {
        return jedisCluster.sdiff(keys);
    }

    @Override
    public Long sdiffstore(final String dstkey, final String... keys) {
        return jedisCluster.sdiffstore(dstkey, keys);
    }

    @Override
    public Set<String> sinter(final String... keys) {
        return jedisCluster.sinter(keys);
    }

    @Override
    public Long sinterstore(final String dstkey, final String... keys) {
        return jedisCluster.sinterstore(dstkey, keys);
    }

    @Override
    public Long smove(final String srckey, final String dstkey, final String member) {
        return jedisCluster.smove(srckey, dstkey, member);
    }

    @Override
    public Set<String> sunion(final String... keys) {
        return jedisCluster.sunion(keys);
    }

    @Override
    public Long sunionstore(final String dstkey, final String... keys) {
        return jedisCluster.sunionstore(dstkey, keys);
    }

    @Override
    public ScanResult<String> sscan(final String key, final String cursor) {
        return jedisCluster.sscan(key, cursor);
    }

    @Override
    public ScanResult<String> sscan(final String key, final String cursor, final ScanParams params) {
        return jedisCluster.sscan(key, cursor, params);
    }

    @Override
    public Long zadd(final String key, final double score, final String member) {
        return jedisCluster.zadd(key, score, member);
    }

    @Override
    public Long zadd(final String key, final double score, final String member, final ZAddParams params) {
        return jedisCluster.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return jedisCluster.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(final String key, final Map<String, Double> scoreMembers, final ZAddParams params) {
        return jedisCluster.zadd(key, scoreMembers, params);
    }

    @Override
    public Set<String> zrange(final String key, final long start, final long end) {
        return jedisCluster.zrange(key, start, end);
    }

    @Override
    public Long zrem(final String key, final String... member) {
        return jedisCluster.zrem(key, member);
    }

    @Override
    public Double zincrby(final String key, final double score, final String member) {
        return jedisCluster.zincrby(key, score, member);
    }

    @Override
    public Double zincrby(final String key, final double score, final String member, final ZIncrByParams params) {
        return jedisCluster.zincrby(key, score, member, params);
    }

    @Override
    public Long zrank(final String key, final String member) {
        return jedisCluster.zrank(key, member);
    }

    @Override
    public Long zrevrank(final String key, final String member) {
        return jedisCluster.zrevrank(key, member);
    }

    @Override
    public Set<String> zrevrange(final String key, final long start, final long end) {
        return jedisCluster.zrevrange(key, start, end);
    }

    @Override
    public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
        return jedisCluster.zrangeWithScores(key, start, end);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return jedisCluster.zrevrangeWithScores(key, start, end);
    }

    @Override
    public Long zcard(final String key) {
        return jedisCluster.zcard(key);
    }

    @Override
    public Double zscore(final String key, final String member) {
        return jedisCluster.zscore(key, member);
    }

    @Override
    public Long zcount(final String key, final double min, final double max) {
        return jedisCluster.zcount(key, min, max);
    }

    @Override
    public Long zcount(final String key, final String min, final String max) {
        return jedisCluster.zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return jedisCluster.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max) {
        return jedisCluster.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        return jedisCluster.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
        return jedisCluster.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
        return jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
        return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset, final int count) {
        return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset, final int count) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByRank(final String key, final long start, final long end) {
        return jedisCluster.zremrangeByRank(key, start, end);
    }

    @Override
    public Long zremrangeByScore(final String key, final double start, final double end) {
        return jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zremrangeByScore(final String key, final String start, final String end) {
        return jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zlexcount(final String key, final String min, final String max) {
        return jedisCluster.zlexcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(final String key, final String min, final String max) {
        return jedisCluster.zrangeByLex(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
        return jedisCluster.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByLex(final String key, final String max, final String min) {
        return jedisCluster.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByLex(final String key, final String max, final String min, final int offset, final int count) {
        return jedisCluster.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(final String key, final String min, final String max) {
        return jedisCluster.zremrangeByLex(key, min, max);
    }

    @Override
    public Long zunionstore(final String dstkey, final String... sets) {
        return jedisCluster.zunionstore(dstkey, sets);
    }

    @Override
    public ScanResult<Tuple> zscan(final String key, final String cursor) {
        return jedisCluster.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(final String key, final String cursor, final ScanParams params) {
        return jedisCluster.zscan(key, cursor, params);
    }

    @Override
    public Long zunionstore(final String dstkey, final ZParams params, final String... sets) {
        return jedisCluster.zunionstore(dstkey, params, sets);
    }

    @Override
    public Long append(final String key, final String value) {
        return jedisCluster.append(key, value);
    }

    @Override
    public Long bitcount(final String key) {
        return jedisCluster.bitcount(key);
    }

    @Override
    public Long bitcount(final String key, final long start, final long end) {
        return jedisCluster.bitcount(key, start, end);
    }

    @Override
    public Long bitop(final BitOP op, final String destKey, final String... srcKeys) {
        return jedisCluster.bitop(op, destKey, srcKeys);
    }

    @Override
    public Long bitpos(final String key, final boolean value) {
        return jedisCluster.bitpos(key, value);
    }

    @Override
    public Long bitpos(final String key, final boolean value, final BitPosParams params) {
        return jedisCluster.bitpos(key, value, params);
    }

    @Override
    public Long decr(final String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long decrBy(final String key, final long integer) {
        return jedisCluster.decrBy(key, integer);
    }

    @Override
    public Long incrBy(final String key, final long integer) {
        return jedisCluster.incrBy(key, integer);
    }

    @Override
    public Double incrByFloat(final String key, final double value) {
        return jedisCluster.incrByFloat(key, value);
    }

    @Override
    public Long incr(final String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public String get(final String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Boolean getbit(final String key, final long offset) {
        return jedisCluster.getbit(key, offset);
    }

    @Override
    public String getrange(final String key, final long startOffset, final long endOffset) {
        return jedisCluster.getrange(key, startOffset, endOffset);
    }

    @Override
    public String getSet(final String key, final String value) {
        return jedisCluster.getSet(key, value);
    }

    @Override
    public List<String> mget(final String... keys) {
        return jedisCluster.mget(keys);
    }

    @Override
    public String mset(final String... keysvalues) {
        return jedisCluster.mset(keysvalues);
    }

    @Override
    public Long msetnx(final String... keysvalues) {
        return jedisCluster.msetnx(keysvalues);
    }

    @Override
    public String psetex(final String key, final long milliseconds, final String value) {
        return jedisCluster.psetex(key, milliseconds, value);
    }

    @Override
    public String set(final String key, final String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String set(final String key, final String value, final Boolean isNx, final long pxMillSeconds) {
        final String NX = (isNx != null && isNx) ? "NX" : "";
        final String PX = "PX";
        return jedisCluster.set(key, value, NX, PX, pxMillSeconds);
    }

    @Override
    public Boolean setbit(final String key, final long offset, final boolean value) {
        return jedisCluster.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(final String key, final long offset, final String value) {
        return jedisCluster.setbit(key, offset, value);
    }

    @Override
    public String setex(final String key, final int seconds, final String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    @Override
    public Long setnx(final String key, final String value) {
        return jedisCluster.setnx(key, value);
    }

    @Override
    public Long setrange(final String key, final long offset, final String value) {
        return jedisCluster.setrange(key, offset, value);
    }

    @Override
    public String watch(final String... keys) {
        return null;
    }

    @Override
    public String unwatch() {
        return null;
    }

    @Override
    public String clientSetname(String name) {
        return null;
    }

    @Override
    public String clientGetname() {
        return null;
    }

    @Override
    public String clientKill(String client) {
        return null;
    }

    @Override
    public String clientList() {
        return null;
    }

    @Override
    public List<Object> pipelined(RedisPipelineCallback callback) {
        return callback.run(jedisCluster);
    }

    @Override
    public List<Object> multi(RedisTransactionCallback callback) {
        return Collections.emptyList();
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return jedisCluster.geoadd(key, longitude, latitude, member);
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return jedisCluster.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return jedisCluster.geodist(key, member1, member2);
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return jedisCluster.geodist(key, member1, member2, unit);
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return jedisCluster.geohash(key, members);
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return jedisCluster.geopos(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return jedisCluster.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedisCluster.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return jedisCluster.georadiusByMember(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return jedisCluster.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return jedisCluster.hsetnx(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return jedisCluster.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return jedisCluster.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return jedisCluster.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return jedisCluster.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return jedisCluster.hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

    @Override
    public Long hlen(String key) {
        return jedisCluster.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return jedisCluster.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return jedisCluster.hscan(key, cursor, params);
    }
}
