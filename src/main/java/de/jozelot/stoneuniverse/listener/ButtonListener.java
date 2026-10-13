package de.jozelot.stoneuniverse.listener;

import de.jozelot.stoneuniverse.StoneUniverse;
import de.jozelot.stoneuniverse.mechanics.server.Player;
import de.jozelot.stoneuniverse.util.Messages;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class ButtonListener extends ListenerAdapter {

    private final StoneUniverse bot;

    public ButtonListener(StoneUniverse bot) {
        this.bot = bot;
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String componentId = event.getComponentId();

        if (componentId.startsWith("online:page:")) {
            String targetPageStr = componentId.substring("online:page:".length());
            if (targetPageStr.equals("current")) {
                event.deferEdit().queue();
                return;
            }

            int targetPage;
            try {
                targetPage = Integer.parseInt(targetPageStr);
            } catch (NumberFormatException e) {
                event.deferEdit().queue();
                return;
            }

            var server = bot.getBootstrap().getServer();
            List<Player> players = server.getPlayers().values().stream().toList();
            int playerCount = players.size();
            int maxPlayers = server.getMaxPlayers();

            event.editComponents(Messages.getOnlineInfo(playerCount, maxPlayers, players, targetPage))
                    .useComponentsV2()
                    .queue();
        }
    }
}
