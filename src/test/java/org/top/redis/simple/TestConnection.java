package org.top.redis.simple;

import org.junit.Test;
import org.top.redis.connection.RedisConnection;
import org.top.redis.connection.RedisConnectionFactory;
import org.top.redis.pipeline.RedisPipeline;
import org.top.redis.pipeline.RedisPipelineCallback;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yubin on 16/4/13.
 */
public class TestConnection {
    RedisConnection connection = RedisConnectionFactory.getInstance().getRedisConnection("secoo-cloud");

    @Test
    public void TestConnectionSet() {
        connection.set("T_KEY", "redis-api");
        //connection.select(1);
        connection.set("T_KEY", "redis-api111");
        System.out.println(connection.get("T_KEY"));
        System.out.println(connection.clientList());
    }

    @Test
    public void TestPipeline() {
        List<Object> result = connection.pipelined(new RedisPipelineCallback() {
            @Override
            public void execute(RedisPipeline pipeline) {
                for (int i = 0; i < 1000; i++) {
                    pipeline.set("T_KEY1", "1");
                    pipeline.set("T_KEY2", "2");
                    pipeline.set("T_KEY3", "3");
                    pipeline.get("T_KEY1");
                    pipeline.get("T_KEY2");
                    pipeline.get("T_KEY3");
                }
            }
        });
        System.out.println(Arrays.toString(result.toArray()));
    }
}
