package org.top.redis.core;

import java.util.List;

/**
 * Created by yubin on 16/3/25.
 */
public interface RedisScriptingCommands {
    Object eval(String script, int keyCount, String... params);

    Object eval(String script, List<String> keys, List<String> args);

    Object eval(String script);

    Object evalsha(String script);

    Object evalsha(String sha1, List<String> keys, List<String> args);

    Object evalsha(String sha1, int keyCount, String... params);

    Boolean scriptExists(String sha1, final String key);

    List<Boolean> scriptExists(final String key, String... sha1);

    String scriptLoad(String script, final String key);
}
