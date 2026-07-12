package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.shared.permissions.EnumPermissions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommand {

    public void execute(CommandSender sender) {

        if (!sender.hasPermission(EnumPermissions.CLEAR_CHAT.getPermission())) {
            sender.sendMessage(
                    Component.text("You do not have permission to use this command.", NamedTextColor.RED));
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!player.hasPermission(EnumPermissions.CLEAR_CHAT_BYPASS.getPermission())) {

                for (int i = 0; i < 100; i++) {
                    player.sendMessage(Component.empty());
                }

                player.sendMessage(
                        Component.text("Chat has been cleared.", NamedTextColor.YELLOW));
            }
        }

        sender.sendMessage(
                Component.text("Chat cleared.", NamedTextColor.GREEN));
    }
}