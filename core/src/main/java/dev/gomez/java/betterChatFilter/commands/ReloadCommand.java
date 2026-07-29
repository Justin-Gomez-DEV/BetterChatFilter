package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

@Command(name = "betterchatfilter", aliases = {"bcf"})
public class ReloadCommand {

    private final ConfigManager configManager;

    public ReloadCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Execute(name = "reload")
    @Permission("betterchatfilter.reload")
    public void reload(@Context CommandSender sender) {
        configManager.reload();

        sender.sendMessage(
                Component.text("[BetterChatFilter] Config reloaded successfully!", NamedTextColor.GREEN));
    }
}
