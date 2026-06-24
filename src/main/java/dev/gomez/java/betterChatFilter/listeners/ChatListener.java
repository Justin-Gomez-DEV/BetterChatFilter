package dev.gomez.java.betterChatFilter.listeners;

import dev.gomez.java.betterChatFilter.FilterEngine;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class ChatListener implements Listener {

    private final FilterEngine filterEngine;

    public ChatListener(FilterEngine filterEngine) {
        this.filterEngine = filterEngine;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event){
        String message = PlainTextComponentSerializer.plainText().serialize(event.message());
        UUID playerId = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();

        if(filterEngine.check(message, playerId, player)){
            event.setCancelled(true);
        }
    }
}