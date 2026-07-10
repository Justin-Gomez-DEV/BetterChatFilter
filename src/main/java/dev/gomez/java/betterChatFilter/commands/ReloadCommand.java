package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

        if(sender.hasPermission("betterchatfilter.reload")) {
            if(args.length > 0 && args[0].equalsIgnoreCase("reload")) {

                configManager.reload();

                sender.sendMessage(Component.text("[BetterChatFilter] Config reloaded successfully!").color(NamedTextColor.GREEN));

                return true;

            }
        }

        return false;
    }
}
