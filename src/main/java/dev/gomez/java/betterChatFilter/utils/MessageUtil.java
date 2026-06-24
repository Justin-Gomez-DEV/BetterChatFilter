package dev.gomez.java.betterChatFilter.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class MessageUtil {
    public static void send(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
