package dev.gomez.java.betterChatFilter;

import dev.gomez.java.betterChatFilter.commands.BCFCommand;
import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.listeners.ChatListener;
import dev.gomez.java.betterChatFilter.listeners.PlayerJoinListener;
import dev.gomez.java.betterChatFilter.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterChatFilter extends JavaPlugin {

    @Override
    public void onEnable() {

        int pluginId = 32825;
        Metrics metrics = new Metrics(this, pluginId);

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

        if (getCommand("bcf") != null) {
            getCommand("bcf").setExecutor(
                    new BCFCommand(configManager)
            );
        }

        getServer().getScheduler().runTaskAsynchronously(this, () -> {

            UpdateChecker.check(getPluginMeta().getVersion(), this);

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