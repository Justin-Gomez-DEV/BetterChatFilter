package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.shared.permissions.EnumPermissions;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "betterchatfilter", aliases = {"bcf"})
public class BCFCommand {

    private ConfigManager configManager;

    public BCFCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Execute
    public void execute(@Context CommandSender sender) {
        help(sender);
    }

    @Execute(name = "help")
    public void help(@Context CommandSender sender) {
        sender.sendMessage(Component.text("=== BetterChatFilter Help ===", NamedTextColor.GREEN));
        sender.sendMessage(Component.empty());

        sender.sendMessage(Component.text("/bcf reload - Reload the config", NamedTextColor.GRAY));

        sender.sendMessage(Component.text("/bcf clearchat or clear - Clear the chat", NamedTextColor.GRAY));

        sender.sendMessage(Component.text("/bcf help - Show this menu", NamedTextColor.GRAY));
    }

    @Execute(name = "reload")
    @Permission("betterchatfilter.reload")
    public void reload(@Context CommandSender sender) {

        configManager.reload();

        sender.sendMessage(
                Component.text("[BetterChatFilter] Config reloaded successfully!", NamedTextColor.GREEN));
    }

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
