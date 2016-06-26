package org.top.redis.exception;

/**
 * Created by yubin on 16/3/28.
 */
public class RedisException extends RuntimeException {

    public RedisException() {

    }

    public RedisException(String s) {
        super(s);
    }

    public RedisException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RedisException(Throwable throwable) {
        super(throwable);
    }
}
