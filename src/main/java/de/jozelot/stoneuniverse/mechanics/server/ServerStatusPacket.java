package de.jozelot.stoneuniverse.mechanics.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.jozelot.stoneuniverse.interfaces.Packet;

import java.util.List;

public class ServerStatusPacket implements Packet {
    private double tps;
    private double mspt;
    private List<PlayerDto> players;
    private int maxPlayers;

    public double getTps() { return tps; }
    public double getMspt() { return mspt; }
    public List<PlayerDto> getPlayers() { return players; }
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public String getId() {
        return "ServerStatusPacket";
    }

    public static class PlayerDto {
        private String uuid;
        private String name;
        private String lpRank;
        private String lpName;

        public String getUuid() { return uuid; }
        public String getName() { return name; }
        public String getLpRank() { return lpRank; }
        public String getLpName() { return lpName; }
    }
}