package org.top.redis.simple;

import org.junit.Test;
import org.top.redis.connection.RedisConnection;
import org.top.redis.connection.RedisConnectionFactory;

/**
 * Created by yubin on 16/4/13.
 */
public class TestPubSub {

    @Test
    public void TestConnectionSet() {
        RedisConnection connection = RedisConnectionFactory.getInstance().getRedisConnection("secoo-freeze");
        connection.set("T_KEY", "redis-api");
        System.out.println(connection.get("T_KEY"));
    }
}
