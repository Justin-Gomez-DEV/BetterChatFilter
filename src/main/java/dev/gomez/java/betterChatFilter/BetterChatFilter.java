package dev.gomez.java.betterChatFilter;

import dev.gomez.java.betterChatFilter.commands.*;
import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.listeners.ChatListener;
import dev.gomez.java.betterChatFilter.listeners.PlayerJoinListener;
import dev.gomez.java.betterChatFilter.utils.UpdateChecker;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.message.LiteMessages;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterChatFilter extends JavaPlugin {

    private LiteCommands<CommandSender> liteCommands;

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

        getLogger().info("Registering LiteCommands...");
        this.liteCommands = LiteBukkitFactory.builder("betterchatfilter", this)
                .commands(
                        new BCFCommand(configManager)
                )
                .message(LiteMessages.MISSING_PERMISSIONS, permissions -> "Required permissions: (" + permissions.asJoinedText() + ")")
                .build();

        getLogger().info("LiteCommands registered!");

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
        if (this.liteCommands != null) {
            this.liteCommands.unregister();
        }
    }
}