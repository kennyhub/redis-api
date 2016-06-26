package org.top.redis.connection;

import redis.client.RedisException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Pool;

/**
 * Created by yubin on 16/4/12.
 */
public abstract class RedisSingleCommand<T> {
    private Pool<Jedis> pool;

    public RedisSingleCommand(Pool<Jedis> pool) {
        this.pool = pool;
    }

    public abstract T execute(Jedis connection);

    public T run() {
        Jedis connection = null;
        try {
            connection = pool.getResource();
            return execute(connection);
        } catch (JedisConnectionException e) {
            throw new RedisException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    private void releaseConnection(Jedis connection) {
        if (connection != null) {
            connection.close();
        }
    }

}
