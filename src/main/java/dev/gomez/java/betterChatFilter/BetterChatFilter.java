package dev.gomez.java.betterChatFilter;

import dev.gomez.java.betterChatFilter.commands.BCFCommand;
import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.listeners.ChatListener;
import dev.gomez.java.betterChatFilter.listeners.PlayerJoinListener;
import dev.gomez.java.betterChatFilter.utils.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterChatFilter extends JavaPlugin {

    @Override
    public void onEnable() {

        ConfigManager configManager = new ConfigManager(this);
        configManager.init();

        FilterEngine filterEngine = new FilterEngine(configManager);

        getServer().getPluginManager().registerEvents(
                new ChatListener(filterEngine),
                this
        );

        getServer().getPluginManager().registerEvents(
                new PlayerJoinListener(),
                this
        );

        getCommand("bcf").setExecutor(
                new BCFCommand(configManager)
        );

        getServer().getScheduler().runTaskAsynchronously(this, () -> {

            UpdateChecker.check(getPluginMeta().getVersion());

            if (UpdateChecker.isUpdateAvailable()) {
                getLogger().warning(
                        "A new update is available! Latest version: "
                                + UpdateChecker.getLatestVersion()
                );
            }
        });
    }

    @Override
    public void onDisable() {

    }
}