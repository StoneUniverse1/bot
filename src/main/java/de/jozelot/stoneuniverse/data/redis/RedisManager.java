package de.jozelot.stoneuniverse.data.redis;

import de.jozelot.stoneuniverse.StoneUniverse;
import de.jozelot.stoneuniverse.core.BotBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class RedisManager extends JedisPubSub {

    private static final Logger logger = LoggerFactory.getLogger(RedisManager.class);
    private final StoneUniverse bot;

    public RedisManager(StoneUniverse bot) {
        this.bot = bot;
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }
}
