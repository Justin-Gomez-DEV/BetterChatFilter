package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.shared.permissions.EnumPermissions;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "betterchatfilter", aliases = {"bcf"})
public class ClearChatCommand {

    @Execute(name = "chatclear", aliases = {"clear"})
    @Permission("betterchatfilter.chatclear")
    public void chatclear(@Context CommandSender sender) {
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
