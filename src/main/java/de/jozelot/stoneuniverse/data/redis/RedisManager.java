package de.jozelot.stoneuniverse.data.redis;

import de.jozelot.stoneuniverse.StoneUniverse;
import de.jozelot.stoneuniverse.interfaces.RedisListener;
import de.jozelot.stoneuniverse.listener.redis.OnlinePacketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisManager extends JedisPubSub {

    private static final Logger logger = LoggerFactory.getLogger(RedisManager.class);
    private final StoneUniverse bot;

    private final Map<String, RedisListener> listeners = new HashMap<>();

    public RedisManager(StoneUniverse bot) {
        this.bot = bot;
        registerListeners();
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        RedisListener listener = listeners.get(channel);
        if (listener == null) return;

        listener.execute(message);
    }

    private void registerListeners() {
        List<RedisListener> tempListeners = new ArrayList<>();

        // LISTENER REGISTRATION
        tempListeners.add(new OnlinePacketListener(bot));

        for (RedisListener listener : tempListeners) {
            listeners.put(listener.getChannel(), listener);
        }
    }
}
