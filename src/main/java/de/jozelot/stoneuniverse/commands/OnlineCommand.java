package de.jozelot.stoneuniverse.commands;

import de.jozelot.stoneuniverse.StoneUniverse;
import de.jozelot.stoneuniverse.interfaces.Command;
import de.jozelot.stoneuniverse.mechanics.server.Player;
import de.jozelot.stoneuniverse.util.Messages;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OnlineCommand implements Command {

    private final StoneUniverse bot;
    private static final Logger logger = LoggerFactory.getLogger(OnlineCommand.class);

    public OnlineCommand(StoneUniverse bot) {
        this.bot = bot;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("online", "\uD83C\uDF10 | Zeigt an wer auf dem Server online ist.");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        var server = bot.getBootstrap().getServer();

        List<Player> players = server.getPlayers().values().stream().toList();
        int playerCount = players.size();
        int maxPlayers = server.getMaxPlayers();

        event.replyComponents(Messages.getOnlineInfo(playerCount, maxPlayers, players, 1))
                .useComponentsV2()
                .setEphemeral(true)
                .queue();
    }

    @Override
    public String getGuildId() {
        long id = bot.getBootstrap().getConfig().getBotConf().getGuildId();
        return String.valueOf(id);
    }
}
