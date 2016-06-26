package org.top.redis.connection;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.top.redis.constants.CLUSTER_MODE;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.*;

/**
 * Created by yubin on 16/3/25.
 */
public class RedisConnectionFactory {

    private final static Log log = LogFactory.getLog(RedisConnectionFactory.class);


    private static class LazyHolder {
        private static final RedisConnectionFactory INSTANCE = new RedisConnectionFactory();
    }

    public static RedisConnectionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    private RedisConnectionFactory() {
        initAppRedis();
    }

    private Map<String, RedisConnection> appRedisMap = new HashMap<String, RedisConnection>();

    public RedisConnection getRedisConnection(String appname) {
        return appRedisMap.get(appname);
    }

    private void initAppRedis() {
        try {
            XMLConfiguration routingConfig = new XMLConfiguration("appredis.xml");
            HashMap<String, Set<RedisUser>> redisUsers = new HashMap<String, Set<RedisUser>>();
            List<Object> serverNodes = routingConfig.getList("servers.node.id");
            Set<RedisUser> redisUserSet = null;
            for (int ci = 0; ci < serverNodes.size(); ci++) {
                String nodeId = (String) serverNodes.get(ci);
                String hostsStr = routingConfig.getString("servers.node(" + ci + ").hosts");
                String password = routingConfig.getString("servers.node(" + ci + ").password");
                password = (password != null && password.trim().length() == 0) ? null : password;

                if (hostsStr == null || hostsStr.trim().length() == 0) {
                    continue;
                }

                String[] hosts = hostsStr.split(";", -1);
                redisUserSet = new HashSet<RedisUser>();
                for (String hostStr : hosts) {
                    if (hostStr.trim().length() == 0) {
                        continue;
                    }
                    String[] hostAndPort = hostStr.split(":", -1);
                    String host = hostAndPort[0];
                    int port = Protocol.DEFAULT_PORT;
                    if (hostAndPort.length >= 2) {
                        try {
                            port = Integer.parseInt(hostAndPort[1], 10);
                        } catch (Exception e) {
                        }
                    }
                    redisUserSet.add(new RedisUser(host, port, password));
                }
                redisUsers.put(nodeId, redisUserSet);
            }
            List<Object> appNodes = routingConfig.getList("apps.node.id");
            HashMap<String, RedisConnection> connectionApps = new HashMap<String, RedisConnection>();
            for (int ci = 0; ci < appNodes.size(); ci++) {
                String nodeId = (String) appNodes.get(ci);
                String servers = routingConfig.getString("apps.node(" + ci + ").servers", "");
                CLUSTER_MODE model = CLUSTER_MODE.getClusterMode(routingConfig.getString("apps.node(" + ci + ").model"));
                if (servers.indexOf(",") >= 0 && CLUSTER_MODE.SINGLE.equals(model)) {
                    servers = servers.split(",", 10)[0];
                }
                int maxTotal = routingConfig.getInt("apps.node(" + ci + ").pool.maxTotal", JedisPoolConfig.DEFAULT_MAX_TOTAL);
                int maxIdle = routingConfig.getInt("apps.node(" + ci + ").pool.maxIdle", JedisPoolConfig.DEFAULT_MAX_IDLE);
                long maxWait = routingConfig.getLong("apps.node(" + ci + ").pool.maxWait", JedisPoolConfig.DEFAULT_MAX_WAIT_MILLIS);
                int timeout = routingConfig.getInt("apps.node(" + ci + ").pool.timeout", Protocol.DEFAULT_TIMEOUT);
                int dbindex = routingConfig.getInt("apps.node(" + ci + ").dbIndex", Protocol.DEFAULT_DATABASE);

                RedisConnection redisConnection = new RedisConnection(model, getRedisUsers(servers, redisUsers), nodeId, timeout, maxTotal, maxIdle, maxWait, dbindex);
                connectionApps.put(nodeId, redisConnection);
            }
            this.appRedisMap = connectionApps;
        } catch (Exception e) {
            log.error("Redis配置初始化失败", e);
        }
    }

    private Set<RedisUser> getRedisUsers(String servers, HashMap<String, Set<RedisUser>> redisUserMap) {
        Set<RedisUser> redisUsers = new HashSet<RedisUser>();
        List<String> serverSet = new ArrayList<String>(Arrays.asList(servers.split(",", -1)));
        for (Map.Entry<String, Set<RedisUser>> entry : redisUserMap.entrySet()) {
            if (serverSet.contains(entry.getKey())) {
                redisUsers.addAll(entry.getValue());
            }
        }
        return redisUsers;
    }

}