package de.jozelot.stoneuniverse.interfaces;

public interface RedisListener {

    void execute();
    String getChannel();
}
