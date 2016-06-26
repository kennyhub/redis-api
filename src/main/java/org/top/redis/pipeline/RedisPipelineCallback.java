package org.top.redis.pipeline;

import org.top.redis.connection.RedisCallback;
import redis.client.RedisException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * Created by yubin on 16/4/12.
 */
public abstract class RedisPipelineCallback implements RedisCallback {

    public abstract void execute(RedisPipeline pipeline);

    public List<Object> run(Jedis connection) {
        RedisPipeline pipeline = new RedisPipeline();
        try {
            pipeline.setClient(connection.getClient());
            execute(pipeline);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
        }
        return pipeline.syncAndReturnAll();
    }

    public List<Object> run(JedisCluster cluster) {
        RedisPipeline pipeline = new RedisPipeline();
        try {
            pipeline.setJedisCluster(cluster);
            execute(pipeline);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
        }
        return pipeline.syncAndReturnAll();
    }
}
