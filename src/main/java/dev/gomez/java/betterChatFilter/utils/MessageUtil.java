package dev.gomez.java.betterChatFilter.utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public final class MessageUtil {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static void send(Player player, String message){

        if (message.contains("<#")) {
            player.sendMessage(MINI_MESSAGE.deserialize(message));
        } else {
            player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(message));
        }
    }
}
