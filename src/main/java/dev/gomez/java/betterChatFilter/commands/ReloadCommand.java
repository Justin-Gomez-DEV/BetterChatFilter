package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.shared.permissions.EnumPermissions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand {

    private final ConfigManager configManager;

    public ReloadCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void execute(CommandSender sender) {

        if (!sender.hasPermission(EnumPermissions.RELOAD.getPermission())) {
            sender.sendMessage(
                    Component.text("You do not have permission to use this command.", NamedTextColor.RED));
            return;
        }

        configManager.reload();

        sender.sendMessage(
                Component.text("[BetterChatFilter] Config reloaded successfully!", NamedTextColor.GREEN));
    }
}