package de.jozelot.stoneuniverse.listener.redis;

import de.jozelot.stoneuniverse.StoneUniverse;
import de.jozelot.stoneuniverse.data.redis.RedisManager;
import de.jozelot.stoneuniverse.interfaces.RedisListener;
import de.jozelot.stoneuniverse.mechanics.server.Player;
import de.jozelot.stoneuniverse.mechanics.server.ServerStatusPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OnlinePacketListener implements RedisListener {

    private static final Logger logger = LoggerFactory.getLogger(OnlinePacketListener.class);
    private final ObjectMapper mapper = JsonMapper.builder().build();
    private final StoneUniverse bot;

    public OnlinePacketListener(StoneUniverse bot) {
        this.bot = bot;
    }

    @Override
    public void execute(String payload) {
        try {
            ServerStatusPacket packet = mapper.readValue(payload, ServerStatusPacket.class);

            var server = bot.getBootstrap().getServer();
            server.setOnline(true);
            server.setTps(packet.getTps());
            server.setMspt(packet.getMspt());
            server.setMaxPlayers(packet.getMaxPlayers());

            Map<UUID, Player> playerMap = new HashMap<>();
            for (ServerStatusPacket.PlayerDto dto : packet.getPlayers()) {
                UUID uuid = UUID.fromString(dto.getUuid());
                Player player = new Player(uuid, dto.getName(), dto.getLpRank(), dto.getLpName());
                playerMap.put(uuid, player);
            }

            server.setPlayers(playerMap);
            server.setUpdate();

            // logger.info("Server Status empfangen: {}", mapper.writeValueAsString(packet));
            // logger.info("Exact Packet: {}", payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getChannel() {
        return "network:OnlinePacket";
    }
}
