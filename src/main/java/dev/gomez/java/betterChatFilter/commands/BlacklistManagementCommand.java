package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

import java.util.List;

@Command(name = "betterchatfilter", aliases = {"bcf"})
public class BlacklistManagementCommand {

    private final ConfigManager configManager;

    public BlacklistManagementCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Execute(name = "blacklist add")
    @Permission("betterchatfilter.filter.add")
    public void blacklistAdd(@Context CommandSender sender, @Arg String filterType, @Arg String badWord){
        List<String> filter = configManager.getBlacklistConfig().getStringList(filterType);

        filter.add(badWord);

        configManager.getBlacklistConfig().set(filterType, filter);
        configManager.save(configManager.getBlacklistConfig(), configManager.getBlacklistFile());

        sender.sendMessage(Component.text("[BetterChatFilter] Config saved successfully!", NamedTextColor.GREEN));
    }

    @Execute(name = "blacklist remove")
    @Permission("betterchatfilter.filter.remove")
    public void blacklistRemove(@Context CommandSender sender, @Arg String filterType, @Arg String word) {
        List<String> filter = configManager.getBlacklistConfig().getStringList(filterType);

        filter.remove(word);

        configManager.getBlacklistConfig().set(filterType, filter);
        configManager.save(configManager.getBlacklistConfig(), configManager.getBlacklistFile());

        sender.sendMessage(Component.text("[BetterChatFilter] Config saved successfully!", NamedTextColor.GREEN));
    }
}
