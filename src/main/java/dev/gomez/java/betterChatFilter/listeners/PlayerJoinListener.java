package dev.gomez.java.betterChatFilter.listeners;

import dev.gomez.java.betterChatFilter.utils.MessageUtil;
import dev.gomez.java.betterChatFilter.utils.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(event.getPlayer().hasPermission("betterchatfilter.notify.update")){
            if (UpdateChecker.isUpdateAvailable()){
                MessageUtil.send(event.getPlayer(), "[BetterChatFilter] A new update is available! Download it here: https://www.spigotmc.org/resources/136505");
            }
        }
    }
}
