package dev.gomez.java.betterChatFilter.filters;

import dev.gomez.java.betterChatFilter.config.ConfigManager;

import java.util.List;

public class AdvertisingFilter extends BaseFilter{
    private ConfigManager configManager;

    public AdvertisingFilter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean filter(String message){
        boolean isEnabled = configManager.getPlugin().getConfig().getBoolean("filters.advertising.enabled");
        if(isEnabled) {

            List<String> blacklistedAdvertisings = configManager.getBlacklistConfig().getStringList("advertising");

            for (String advertising : blacklistedAdvertisings) {
                if(message.contains(advertising)){
                    return true;
                }
            }

        }

        return false;
    }
}
