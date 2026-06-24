package dev.gomez.java.betterChatFilter;

import dev.gomez.java.betterChatFilter.commands.ReloadCommand;
import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.listeners.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterChatFilter extends JavaPlugin {

    @Override
    public void onEnable() {

        ConfigManager configManager = new ConfigManager(this);
        configManager.init();
        FilterEngine filterEngine = new FilterEngine(configManager);

        getServer().getPluginManager().registerEvents(new ChatListener(filterEngine), this);

        getCommand("bcf").setExecutor(new ReloadCommand(configManager));
    }

    @Override
    public void onDisable() {

    }
}
