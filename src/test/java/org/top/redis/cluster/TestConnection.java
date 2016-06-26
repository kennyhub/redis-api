package org.top.redis.cluster;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.top.redis.connection.RedisConnection;
import org.top.redis.connection.RedisConnectionFactory;
import org.top.redis.pipeline.RedisPipeline;
import org.top.redis.pipeline.RedisPipelineCallback;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by yubin on 16/4/13.
 */
public class TestConnection {

    RedisConnection connection = RedisConnectionFactory.getInstance().getRedisConnection("secoo-freeze");

    @Test
    public void TestConnectionSet() {
        connection.set("T_KEY", "redis-api");
        System.out.println(connection.get("T_KEY"));
    }

    @Test
    public void TestPipeline() {
        List<Object> result = connection.pipelined(new RedisPipelineCallback() {
            @Override
            public void execute(RedisPipeline pipeline) {
                for (int i = 0; i < 500; i++) {
                    pipeline.set("T_KEY1", "1");
                    pipeline.set("T_KEY2", "2");
                    pipeline.set("T_KEY3", "3");
                    pipeline.get("T_KEY1");
                    pipeline.get("T_KEY2");
                    pipeline.get("T_KEY3");
                }
            }
        });
        System.out.println("result length: " + result.size());
        System.out.println(Arrays.toString(result.toArray()));
    }

    @Test
    public void TestNoPipeline() {
        long start = System.currentTimeMillis();
        List<Object> result = new ArrayList<Object>();
        for (int i = 0; i < 500; i++) {
            result.add(connection.get("T_KEY3"));
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        System.out.println("result length: " + result.size());
        System.out.println(Arrays.toString(result.toArray()));

    }

    @Test
    public void TestCustomConnection() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(2);
        config.setTestOnBorrow(true);


        //10.185.240.142:7001;10.185.240.142:7002;10.185.240.142:7003;10.185.240.142:7004;10.185.240.142:7005;10.185.240.142:7006

        HostAndPort hp0 = new HostAndPort("10.185.240.142", 7001);
        HostAndPort hp1 = new HostAndPort("10.185.240.142", 7002);
        HostAndPort hp2 = new HostAndPort("10.185.240.142", 7003);
        HostAndPort hp3 = new HostAndPort("10.185.240.142", 7004);
        HostAndPort hp4 = new HostAndPort("10.185.240.142", 7005);
        HostAndPort hp5 = new HostAndPort("10.185.240.142", 7006);

        Set<HostAndPort> hps = new HashSet<HostAndPort>();
        hps.add(hp0);
        hps.add(hp1);
        hps.add(hp2);
        hps.add(hp3);
        hps.add(hp4);
        hps.add(hp5);

        // 超时，最大的转发数，最大链接数，最小链接数都会影响到集群
        JedisCluster jedisCluster = new JedisCluster(hps, 5000, 10, config);

//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            jedisCluster.set("sn" + i, "n" + i);
//        }
//        long end = System.currentTimeMillis();
//
//        System.out.println("Simple  @ Sharding Set : " + (end - start) / 10000);
//
//        for (int i = 0; i < 100; i++) {
//            System.out.println(jedisCluster.get("sn" + i));
//        }


//        List<String> result = jedisCluster.mget("sn0", "sn1");
//        System.out.println(result);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            jedisCluster.get("T_KEY");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - startTime));


    }

    @Test
    public void test111() throws IOException, InterruptedException {
        int i= 0;
        while(true){
            List<Object> result = connection.pipelined(new RedisPipelineCallback() {
                @Override
                public void execute(RedisPipeline pipeline) {
                    pipeline.set("T_KEY1", "1");
                    pipeline.set("T_KEY2", "2");
                    pipeline.set("T_KEY3", "3");
                    pipeline.get("T_KEY1");
                    pipeline.get("T_KEY2");
                    pipeline.get("T_KEY3");
                }
            });
            //System.out.println(connection.set("T_KEY3", "lll"));
            System.out.println(i++);
            System.out.println(Arrays.toString(result.toArray()));
            Thread.sleep(1);
        }
    }

}
