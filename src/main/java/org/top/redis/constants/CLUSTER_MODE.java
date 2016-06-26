package org.top.redis.constants;

import org.top.redis.exception.RedisException;

/**
 * Created by yubin on 16/4/13.
 */
public enum CLUSTER_MODE {
    CLUSTER("cluster"),
    SINGLE("single");

    private String name;

    CLUSTER_MODE(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CLUSTER_MODE getClusterMode(String name) {
        if (name == null) {
            return SINGLE;
        }
        for (CLUSTER_MODE mode : CLUSTER_MODE.values()) {
            if (name.trim().toLowerCase().equals(mode.getName())) {
                return mode;
            }

        }
        throw new RedisException("redis配置集群模式未知");
    }

}