package dev.gomez.java.betterChatFilter.commands;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BCFCommand implements CommandExecutor {

    private final ConfigManager configManager;

    public BCFCommand(ConfigManager configManager){
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 0) {
            new HelpCommand().execute(sender);
            return true;
        }

        switch(args[0].toLowerCase()) {

            case "help":
                new HelpCommand().execute(sender);
                break;

            case "reload":
                new ReloadCommand(configManager).execute(sender);
                break;

            case "chatclear":
            case "clear":
                new ChatClearCommand().execute(sender);
                break;

            default:
                sender.sendMessage("Unknown subcommand");
        }

        return true;
    }
}
