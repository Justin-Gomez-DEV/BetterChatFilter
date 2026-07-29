package dev.gomez.java.betterChatFilter.commands;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

@Command(name = "betterchatfilter", aliases = {"bcf"})
public class HelpCommand {

    @Execute(name = "help")
    public void help(@Context CommandSender sender) {
        sender.sendMessage(Component.text("=== BetterChatFilter Help ===", NamedTextColor.GREEN));
        sender.sendMessage(Component.empty());

        sender.sendMessage(Component.text("/bcf reload - Reload the config", NamedTextColor.GRAY));

        sender.sendMessage(Component.text("/bcf clearchat or clear - Clear the chat", NamedTextColor.GRAY));

        sender.sendMessage(Component.text("/bcf help - Show this menu", NamedTextColor.GRAY));
    }

}
