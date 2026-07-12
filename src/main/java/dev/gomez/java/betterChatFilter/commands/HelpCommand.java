package dev.gomez.java.betterChatFilter.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {

    public void execute(CommandSender sender) {

        sender.sendMessage(Component.text("=== BetterChatFilter Help ===", NamedTextColor.GREEN));
        sender.sendMessage(Component.empty());

        sender.sendMessage(Component.text("/bcf reload - Reload the config", NamedTextColor.GRAY));

        sender.sendMessage(Component.text("/bcf clear - Clear the chat", NamedTextColor.GRAY));

        sender.sendMessage(Component.text("/bcf help - Show this menu", NamedTextColor.GRAY));
    }

}
