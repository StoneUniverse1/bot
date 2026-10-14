package de.jozelot.stoneuniverse.mechanics.server;

import de.jozelot.stoneuniverse.StoneUniverse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MinecraftServer {

    private final StoneUniverse bot;

    private boolean online = false;
    private double tps = 20.0;
    private double mspt = 0.0;
    private long lastUpdate = 0;
    private Map<UUID, Player> players = new ConcurrentHashMap<>();
    private int maxPlayers;

    public MinecraftServer(StoneUniverse bot) {
        this.bot = bot;
    }

    public boolean isOnline() {
        if (lastUpdate + 10000 < System.currentTimeMillis()) online = false;
        if (!online) {
            setTps(20.0);
            setMspt(0.0);
            setMaxPlayers(0);
            players.clear();
        }
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public double getTps() {
        return tps;
    }

    public void setTps(double tps) {
        this.tps = tps;
    }

    public double getMspt() {
        return mspt;
    }

    public void setMspt(double mspt) {
        this.mspt = mspt;
    }

    public Map<UUID, Player> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    public void setPlayers(Map<UUID, Player> players) {
        this.players = players;
    }

    public void setUpdate() {
        lastUpdate = System.currentTimeMillis();
    }
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
}
