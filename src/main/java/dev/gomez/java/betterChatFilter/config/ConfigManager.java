package dev.gomez.java.betterChatFilter.config;

import dev.gomez.java.betterChatFilter.BetterChatFilter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    private BetterChatFilter betterChatFilter;

    private FileConfiguration blacklistConfig;
    private File blacklistFile;
    private FileConfiguration messagesConfig;
    private File messagesFile;
    
    public FileConfiguration getBlacklistConfig() {
        return blacklistConfig;
    }

    public File getBlacklistFile() {
        return blacklistFile;
    }

    public BetterChatFilter getPlugin() {
        return betterChatFilter;
    }

    public File getMessagesFile() {
        return messagesFile;
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    public ConfigManager (BetterChatFilter betterChatFilter) {
        this.betterChatFilter = betterChatFilter;
    }

    public void init(){
        betterChatFilter.saveDefaultConfig();
        betterChatFilter.saveResource("blacklist.yml", false);
        betterChatFilter.saveResource("messages.yml", false);

        this.blacklistFile = new File(betterChatFilter.getDataFolder(), "blacklist.yml");

        this.messagesFile = new File(betterChatFilter.getDataFolder(), "messages.yml");

        reload();
    }

    public void reload() {
        betterChatFilter.reloadConfig();

        this.blacklistConfig = YamlConfiguration.loadConfiguration(this.blacklistFile);

        this.messagesConfig = YamlConfiguration.loadConfiguration(this.messagesFile);
    }
}
