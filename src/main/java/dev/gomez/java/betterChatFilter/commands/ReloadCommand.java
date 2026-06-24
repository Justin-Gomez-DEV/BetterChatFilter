package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private ConfigManager configManager;

    public ReloadCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length > 0 && args[0].equalsIgnoreCase("reload")) {

            configManager.init();

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[BetterChatFilter] Config reloaded successfully!"));

            return true;

        }

        return false;
    }
}
