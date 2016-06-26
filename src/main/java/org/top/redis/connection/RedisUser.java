package org.top.redis.connection;

import redis.clients.jedis.HostAndPort;

/**
 * Created by yubin on 16/3/31.
 */
public class RedisUser extends HostAndPort {

    public RedisUser(String host, int port) {
        super(host, port);
    }

    public RedisUser(String host, int port, String password) {
        super(host, port);
        this.password = password;
    }

    public HostAndPort getHostAndPort() {
        return new HostAndPort(super.getHost(), super.getPort());
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "RedisUser{" +
                "host='" + super.getHost() + '\'' +
                "port='" + super.getPort() + '\'' +
                "password='" + password + '\'' +
                '}';
    }
}
