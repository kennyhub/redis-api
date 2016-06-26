package org.top.redis.connection;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.top.redis.constants.CLUSTER_MODE;
import org.top.redis.core.RedisCommands;
import org.top.redis.exception.RedisException;
import org.top.redis.pipeline.RedisPipelineCallback;
import org.top.redis.transaction.RedisTransactionCallback;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Pool;
import redis.clients.util.Slowlog;

import java.util.*;

/**
 * Created by yubin on 16/3/28.
 */
public class RedisConnection implements RedisCommands {

    private String appname = "";
    private int timeout = Protocol.DEFAULT_TIMEOUT;
    private String appnodeId = "";
    private int maxTotal = JedisPoolConfig.DEFAULT_MAX_TOTAL;
    private int maxIdle = JedisPoolConfig.DEFAULT_MAX_IDLE;
    private long maxWait = JedisPoolConfig.DEFAULT_MAX_WAIT_MILLIS;
    private int dbIndex = Protocol.DEFAULT_DATABASE;
    private CLUSTER_MODE clusterMode;
    private Set<RedisUser> redisUsers = new HashSet<RedisUser>();

    private Pool<Jedis> pool;
    private GenericObjectPoolConfig poolConfig;
    private JedisCluster jedisCluster;
    private RedisCommands redisProxy;


    public RedisConnection(CLUSTER_MODE clusterMode, Set<RedisUser> redisUsers, String appname, int timeout, int maxTotal, int maxIdle, long maxWait, int dbIndex) {
        if (redisUsers == null || redisUsers.size() == 0) {
            throw new RedisException("获取redis链接失败.节点用户名端口不存在");
        }
        this.appname = appname;
        this.timeout = timeout;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.maxWait = maxWait;
        this.dbIndex = dbIndex;
        this.clusterMode = clusterMode;
        this.poolConfig = new GenericObjectPoolConfig();
        this.poolConfig.setMaxTotal(getMaxTotal());
        this.poolConfig.setMaxIdle(getMaxIdle());
        this.poolConfig.setMaxWaitMillis(getMaxWait());
        this.poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE);
        this.poolConfig.setTestWhileIdle(true);
        this.poolConfig.setMinEvictableIdleTimeMillis(60L * 1000L);
        this.poolConfig.setTimeBetweenEvictionRunsMillis(30 * 1000L);
        this.poolConfig.setNumTestsPerEvictionRun(-1);

        Iterator<RedisUser> redisUserIterator = redisUsers.iterator();
        RedisConnectionProxy proxy = new RedisConnectionProxy();
        switch (getClusterMode()) {
            case CLUSTER:
                Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
                while (redisUserIterator.hasNext()) {
                    hostAndPorts.add(redisUserIterator.next().getHostAndPort());
                }
                jedisCluster = new JedisCluster(hostAndPorts, timeout, getPoolConfig());
                redisProxy = (RedisCommands) proxy.bind(new RedisClusterConnection(jedisCluster));
                break;
            case SINGLE:
                RedisUser redisUser = redisUserIterator.next();
                pool = new JedisPool(getPoolConfig(), redisUser.getHost(), redisUser.getPort(), getTimeout(), redisUser.getPassword(), getDbIndex());
                redisProxy = (RedisCommands) proxy.bind(new RedisSingleConnection(pool));
                break;
            default:
                break;
        }
    }

    public String getAppname() {
        return appname;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public Set<RedisUser> getRedisUsers() {
        return redisUsers;
    }

    public CLUSTER_MODE getClusterMode() {
        return clusterMode;
    }

    public GenericObjectPoolConfig getPoolConfig() {
        return poolConfig;
    }

    @Override
    public String toString() {
        return "RedisConnection{" +
                "dbIndex=" + dbIndex +
                ", appname='" + appname + '\'' +
                ", timeout=" + timeout +
                ", appnodeId='" + appnodeId + '\'' +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", maxWait=" + maxWait +
                '}';
    }

    @Override
    public String clusterNodes() {
        return redisProxy.clusterNodes();
    }

    @Override
    public String clusterMeet(String ip, int port) {
        return redisProxy.clusterMeet(ip, port);
    }

    @Override
    public String clusterAddSlots(int... slots) {
        return redisProxy.clusterAddSlots(slots);
    }

    @Override
    public String clusterDelSlots(int... slots) {
        return redisProxy.clusterDelSlots(slots);
    }

    @Override
    public String clusterInfo() {
        return redisProxy.clusterInfo();
    }

    @Override
    public List<String> clusterGetKeysInSlot(int slot, int count) {
        return redisProxy.clusterGetKeysInSlot(slot, count);
    }

    @Override
    public String clusterSetSlotNode(int slot, String nodeId) {
        return redisProxy.clusterSetSlotNode(slot, nodeId);
    }

    @Override
    public String clusterSetSlotMigrating(int slot, String nodeId) {
        return redisProxy.clusterSetSlotMigrating(slot, nodeId);
    }

    @Override
    public String clusterSetSlotImporting(int slot, String nodeId) {
        return redisProxy.clusterSetSlotImporting(slot, nodeId);
    }

    @Override
    public String clusterSetSlotStable(int slot) {
        return redisProxy.clusterSetSlotStable(slot);
    }

    @Override
    public String clusterForget(String nodeId) {
        return redisProxy.clusterForget(nodeId);
    }

    @Override
    public String clusterFlushSlots() {
        return redisProxy.clusterFlushSlots();
    }

    @Override
    public Long clusterKeySlot(String key) {
        return redisProxy.clusterKeySlot(key);
    }

    @Override
    public Long clusterCountKeysInSlot(int slot) {
        return redisProxy.clusterCountKeysInSlot(slot);
    }

    @Override
    public String clusterSaveConfig() {
        return redisProxy.clusterSaveConfig();
    }

    @Override
    public String clusterReplicate(String nodeId) {
        return redisProxy.clusterReplicate(nodeId);
    }

    @Override
    public List<String> clusterSlaves(String nodeId) {
        return redisProxy.clusterSlaves(nodeId);
    }

    @Override
    public String clusterFailover() {
        return redisProxy.clusterFailover();
    }

    @Override
    public List<Object> clusterSlots() {
        return redisProxy.clusterSlots();
    }

    @Override
    public String clusterReset(JedisCluster.Reset resetType) {
        return redisProxy.clusterReset(resetType);
    }

    @Override
    public String readonly() {
        return redisProxy.readonly();
    }

    @Override
    public String ping() {
        return redisProxy.ping();
    }

    @Override
    public String quit() {
        return redisProxy.quit();
    }

    @Override
    public String flushDB() {
        return redisProxy.flushDB();
    }

    @Override
    public Long dbSize() {
        return redisProxy.dbSize();
    }

    @Override
    public String select(int index) {
        return redisProxy.select(index);
    }

    @Override
    public String flushAll() {
        return redisProxy.flushAll();
    }

    @Override
    public String auth(String password) {
        return redisProxy.auth(password);
    }

    @Override
    public String save() {
        return redisProxy.save();
    }

    @Override
    public String bgsave() {
        return redisProxy.bgsave();
    }

    @Override
    public String bgrewriteaof() {
        return redisProxy.bgrewriteaof();
    }

    @Override
    public Long lastsave() {
        return redisProxy.lastsave();
    }

    @Override
    public String shutdown() {
        return redisProxy.shutdown();
    }

    @Override
    public String info() {
        return redisProxy.info();
    }

    @Override
    public String info(String section) {
        return redisProxy.info(section);
    }

    @Override
    public String slaveof(String host, int port) {
        return redisProxy.slaveof(host, port);
    }

    @Override
    public String slaveofNoOne() {
        return redisProxy.slaveofNoOne();
    }

    @Override
    public Long getDB() {
        return redisProxy.getDB();
    }

    @Override
    public String debug(DebugParams params) {
        return redisProxy.debug(params);
    }

    @Override
    public String configResetStat() {
        return redisProxy.configResetStat();
    }

    @Override
    public List<String> configGet(String pattern) {
        return redisProxy.configGet(pattern);
    }

    @Override
    public String configSet(String parameter, String value) {
        return redisProxy.configSet(parameter, value);
    }

    @Override
    public String slowlogReset() {
        return redisProxy.slowlogReset();
    }

    @Override
    public Long slowlogLen() {
        return redisProxy.slowlogLen();
    }

    @Override
    public List<Slowlog> slowlogGet() {
        return redisProxy.slowlogGet();
    }

    @Override
    public List<Slowlog> slowlogGet(long entries) {
        return redisProxy.slowlogGet(entries);
    }

    @Override
    public String pfmerge(String destinationKey, String... sourceKeys) {
        return redisProxy.pfmerge(destinationKey, sourceKeys);
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return redisProxy.pfadd(key, elements);
    }

    @Override
    public long pfcount(String key) {
        return redisProxy.pfcount(key);
    }

    @Override
    public Long del(String key) {
        return redisProxy.del(key);
    }

    @Override
    public byte[] dump(String key) {
        return redisProxy.dump(key);
    }

    @Override
    public Boolean exists(String key) {
        return redisProxy.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return redisProxy.expire(key, seconds);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return redisProxy.expireAt(key, unixTime);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisProxy.keys(pattern);
    }

    @Override
    public String migrate(String host, int port, String key, int destinationDb, int timeout) {
        return redisProxy.migrate(host, port, key, destinationDb, timeout);
    }

    @Override
    public Long move(String key, int dbIndex) {
        return redisProxy.move(key, dbIndex);
    }

    @Override
    public Long objectRefcount(String string) {
        return redisProxy.objectRefcount(string);
    }

    @Override
    public String objectEncoding(String string) {
        return redisProxy.objectEncoding(string);
    }

    @Override
    public Long objectIdletime(String string) {
        return redisProxy.objectIdletime(string);
    }

    @Override
    public Long persist(String key) {
        return redisProxy.persist(key);
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return redisProxy.pexpire(key, milliseconds);
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return redisProxy.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(String key) {
        return redisProxy.pttl(key);
    }

    @Override
    public String randomKey() {
        return redisProxy.randomKey();
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return redisProxy.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return redisProxy.renamenx(oldkey, newkey);
    }

    @Override
    public String restore(String key, int ttl, byte[] serializedValue) {
        return redisProxy.restore(key, ttl, serializedValue);
    }

    @Override
    public List<String> sort(String key) {
        return redisProxy.sort(key);
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return redisProxy.sort(key, sortingParameters);
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return redisProxy.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(String key, String dstkey) {
        return redisProxy.sort(key, dstkey);
    }

    @Override
    public Long ttl(String key) {
        return redisProxy.ttl(key);
    }

    @Override
    public String type(String key) {
        return redisProxy.type(key);
    }

    @Override
    public Long waitReplicas(int replicas, long timeout) {
        return redisProxy.waitReplicas(replicas, timeout);
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        return redisProxy.scan(cursor);
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        return redisProxy.scan(cursor, params);
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return redisProxy.blpop(timeout, key);
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return redisProxy.brpop(timeout, key);
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return redisProxy.brpoplpush(source, destination, timeout);
    }

    @Override
    public String lindex(String key, long index) {
        return redisProxy.lindex(key, index);
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return redisProxy.linsert(key, where, pivot, value);
    }

    @Override
    public Long llen(String key) {
        return redisProxy.llen(key);
    }

    @Override
    public String lpop(String key) {
        return redisProxy.lpop(key);
    }

    @Override
    public Long lpush(String key, String... strings) {
        return redisProxy.lpush(key, strings);
    }

    @Override
    public Long lpushx(String key, String... string) {
        return redisProxy.lpushx(key, string);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return redisProxy.lrange(key, start, end);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return redisProxy.lrem(key, count, value);
    }

    @Override
    public String lset(String key, long index, String value) {
        return redisProxy.lset(key, index, value);
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return redisProxy.ltrim(key, start, end);
    }

    @Override
    public String rpop(String key) {
        return redisProxy.rpop(key);
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return redisProxy.rpoplpush(srckey, dstkey);
    }

    @Override
    public Long rpush(String key, String... strings) {
        return redisProxy.rpush(key, strings);
    }

    @Override
    public Long rpushx(String key, String... string) {
        return redisProxy.rpushx(key, string);
    }

    @Override
    public Long del(String... keys) {
        return redisProxy.del(keys);
    }

    @Override
    public Long exists(String... keys) {
        return redisProxy.exists(keys);
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return redisProxy.blpop(timeout, keys);
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return redisProxy.brpop(timeout, keys);
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return redisProxy.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return redisProxy.zinterstore(dstkey, params, sets);
    }

    @Override
    public long pfcount(String... keys) {
        return redisProxy.pfcount(keys);
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        redisProxy.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Long publish(String channel, String message) {
        return redisProxy.publish(channel, message);
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        redisProxy.subscribe(jedisPubSub, channels);
    }

    @Override
    public List<String> pubsubChannels(String pattern) {
        return redisProxy.pubsubChannels(pattern);
    }

    @Override
    public Long pubsubNumPat() {
        return redisProxy.pubsubNumPat();
    }

    @Override
    public Map<String, String> pubsubNumSub(String... channels) {
        return redisProxy.pubsubNumSub(channels);
    }

    @Override
    public String substr(String key, int start, int end) {
        return redisProxy.substr(key, start, end);
    }

    @Override
    public Object eval(String script, int keyCount, String... params) {
        return redisProxy.eval(script, keyCount, params);
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        return redisProxy.eval(script, keys, args);
    }

    @Override
    public Object eval(String script) {
        return redisProxy.eval(script);
    }

    @Override
    public Object evalsha(String script) {
        return redisProxy.evalsha(script);
    }

    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        return redisProxy.evalsha(sha1, keys, args);
    }

    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        return redisProxy.evalsha(sha1, keyCount, params);
    }

    @Override
    public Boolean scriptExists(String sha1, String key) {
        return redisProxy.scriptExists(sha1, key);
    }

    @Override
    public List<Boolean> scriptExists(String key, String... sha1) {
        return redisProxy.scriptExists(key, sha1);
    }

    @Override
    public String scriptLoad(String script, String key) {
        return redisProxy.scriptLoad(script, key);
    }

    @Override
    public Long sadd(String key, String... member) {
        return redisProxy.sadd(key, member);
    }

    @Override
    public Set<String> smembers(String key) {
        return redisProxy.smembers(key);
    }

    @Override
    public Long srem(String key, String... member) {
        return redisProxy.srem(key, member);
    }

    @Override
    public String spop(String key) {
        return redisProxy.spop(key);
    }

    @Override
    public Set<String> spop(String key, long count) {
        return redisProxy.spop(key, count);
    }

    @Override
    public Long scard(String key) {
        return redisProxy.scard(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return redisProxy.sismember(key, member);
    }

    @Override
    public String srandmember(String key) {
        return redisProxy.srandmember(key);
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return redisProxy.srandmember(key, count);
    }

    @Override
    public Long strlen(String key) {
        return redisProxy.strlen(key);
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return redisProxy.sdiff(keys);
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return redisProxy.sdiffstore(dstkey, keys);
    }

    @Override
    public Set<String> sinter(String... keys) {
        return redisProxy.sinter(keys);
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return redisProxy.sinterstore(dstkey, keys);
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return redisProxy.smove(srckey, dstkey, member);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return redisProxy.sunion(keys);
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return redisProxy.sunionstore(dstkey, keys);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return redisProxy.sscan(key, cursor);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return redisProxy.sscan(key, cursor, params);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return redisProxy.zadd(key, score, member);
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return redisProxy.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return redisProxy.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return redisProxy.zadd(key, scoreMembers, params);
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return redisProxy.zrange(key, start, end);
    }

    @Override
    public Long zrem(String key, String... member) {
        return redisProxy.zrem(key, member);
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return redisProxy.zincrby(key, score, member);
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return redisProxy.zincrby(key, score, member, params);
    }

    @Override
    public Long zrank(String key, String member) {
        return redisProxy.zrank(key, member);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return redisProxy.zrevrank(key, member);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return redisProxy.zrevrange(key, start, end);
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return redisProxy.zrangeWithScores(key, start, end);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return redisProxy.zrevrangeWithScores(key, start, end);
    }

    @Override
    public Long zcard(String key) {
        return redisProxy.zcard(key);
    }

    @Override
    public Double zscore(String key, String member) {
        return redisProxy.zscore(key, member);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return redisProxy.zcount(key, min, max);
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return redisProxy.zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return redisProxy.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return redisProxy.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return redisProxy.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return redisProxy.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return redisProxy.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return redisProxy.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return redisProxy.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return redisProxy.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return redisProxy.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return redisProxy.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return redisProxy.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return redisProxy.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return redisProxy.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return redisProxy.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return redisProxy.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return redisProxy.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return redisProxy.zremrangeByRank(key, start, end);
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        return redisProxy.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return redisProxy.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return redisProxy.zlexcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return redisProxy.zrangeByLex(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return redisProxy.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return redisProxy.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return redisProxy.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return redisProxy.zremrangeByLex(key, min, max);
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return redisProxy.zunionstore(dstkey, sets);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return redisProxy.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return redisProxy.zscan(key, cursor, params);
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return redisProxy.zunionstore(dstkey, params, sets);
    }

    @Override
    public Long append(String key, String value) {
        return redisProxy.append(key, value);
    }

    @Override
    public Long bitcount(String key) {
        return redisProxy.bitcount(key);
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return redisProxy.bitcount(key, start, end);
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return redisProxy.bitop(op, destKey, srcKeys);
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return redisProxy.bitpos(key, value);
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return redisProxy.bitpos(key, value, params);
    }

    @Override
    public Long decr(String key) {
        return redisProxy.decr(key);
    }

    @Override
    public Long decrBy(String key, long integer) {
        return redisProxy.decrBy(key, integer);
    }

    @Override
    public Long incrBy(String key, long integer) {
        return redisProxy.incrBy(key, integer);
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return redisProxy.incrByFloat(key, value);
    }

    @Override
    public Long incr(String key) {
        return redisProxy.incr(key);
    }

    @Override
    public String get(String key) {
        return redisProxy.get(key);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return redisProxy.getbit(key, offset);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return redisProxy.getrange(key, startOffset, endOffset);
    }

    @Override
    public String getSet(String key, String value) {
        return redisProxy.getSet(key, value);
    }

    @Override
    public List<String> mget(String... keys) {
        return redisProxy.mget(keys);
    }

    @Override
    public String mset(String... keysvalues) {
        return redisProxy.mset(keysvalues);
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return redisProxy.msetnx(keysvalues);
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return redisProxy.psetex(key, milliseconds, value);
    }

    @Override
    public String set(String key, String value) {
        return redisProxy.set(key, value);
    }

    @Override
    public String set(String key, String value, Boolean isNx, long pxMillSeconds) {
        return redisProxy.set(key, value, isNx, pxMillSeconds);
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return redisProxy.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return redisProxy.setbit(key, offset, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return redisProxy.setex(key, seconds, value);
    }

    @Override
    public Long setnx(String key, String value) {
        return redisProxy.setnx(key, value);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return redisProxy.setrange(key, offset, value);
    }

    @Override
    public String watch(String... keys) {
        return redisProxy.watch(keys);
    }

    @Override
    public String unwatch() {
        return redisProxy.unwatch();
    }

    @Override
    public String clientSetname(String name) {
        return redisProxy.clientSetname(name);
    }

    @Override
    public String clientGetname() {
        return redisProxy.clientGetname();
    }

    @Override
    public String clientKill(String client) {
        return redisProxy.clientKill(client);
    }

    @Override
    public String clientList() {
        return redisProxy.clientList();
    }

    @Override
    public List<Object> multi(RedisTransactionCallback callback) {
        return redisProxy.multi(callback);
    }

    @Override
    public List<Object> pipelined(RedisPipelineCallback callback) {
        return redisProxy.pipelined(callback);
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return redisProxy.geoadd(key, longitude, latitude, member);
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return redisProxy.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return redisProxy.geodist(key, member1, member2);
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return redisProxy.geodist(key, member1, member2, unit);
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return redisProxy.geohash(key, members);
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return redisProxy.geopos(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return redisProxy.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return redisProxy.georadius(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return redisProxy.georadiusByMember(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return redisProxy.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return redisProxy.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return redisProxy.hget(key, field);
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return redisProxy.hsetnx(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return redisProxy.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return redisProxy.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return redisProxy.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return redisProxy.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return redisProxy.hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return redisProxy.hdel(key, field);
    }

    @Override
    public Long hlen(String key) {
        return redisProxy.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return redisProxy.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return redisProxy.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return redisProxy.hgetAll(key);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return redisProxy.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return redisProxy.hscan(key, cursor, params);
    }
}
