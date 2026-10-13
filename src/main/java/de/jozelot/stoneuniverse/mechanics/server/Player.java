package de.jozelot.stoneuniverse.mechanics.server;

import java.util.UUID;

public class Player {

    private UUID uuid;
    private String name;
    private String lpRank, lpName;
    private int lpWeight;

    public Player(UUID uuid, String name, String lpRank, String lpName, int lpWeight) {
        this.uuid = uuid;
        this.name = name;
        this.lpRank = lpRank;
        this.lpName = lpName;
        this.lpWeight = lpWeight;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getLpRank() {
        return lpRank;
    }

    public String getLpName() {
        return lpName;
    }
    public int getLpWeight() {
        return lpWeight;
    }
}
