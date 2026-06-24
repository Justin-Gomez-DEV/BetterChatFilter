package dev.gomez.java.betterChatFilter.filters;

import dev.gomez.java.betterChatFilter.config.ConfigManager;

import java.util.List;

public class LinkFilter extends BaseFilter{
    private ConfigManager configManager;

    public LinkFilter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean filter(String message){
        boolean isEnabled = configManager.getPlugin().getConfig().getBoolean("filters.links.enabled");
        if(isEnabled) {

            List<String> blacklistedLinks = configManager.getBlacklistConfig().getStringList("links");

            for (String link : blacklistedLinks) {
                if(message.contains(link)) {
                    return true;
                }
            }

        }

        return false;
    }
}
