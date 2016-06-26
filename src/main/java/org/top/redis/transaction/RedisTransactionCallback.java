package org.top.redis.transaction;

import org.top.redis.connection.RedisCallback;
import redis.client.RedisException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Created by yubin on 16/4/12.
 */
public abstract class RedisTransactionCallback implements RedisCallback {

    public abstract void execute(Transaction tx);

    public List<Object> run(Jedis connection) {
        Transaction tx = null;
        try {
            tx = connection.multi();
            execute(tx);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {

        }
        return tx.exec();
    }
}
