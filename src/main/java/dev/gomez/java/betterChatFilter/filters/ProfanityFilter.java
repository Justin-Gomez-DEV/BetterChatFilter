package dev.gomez.java.betterChatFilter.filters;

import dev.gomez.java.betterChatFilter.config.ConfigManager;

import java.util.List;

public class ProfanityFilter extends BaseFilter{

    private final ConfigManager configManager;

    public ProfanityFilter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean filter(String message) {
        boolean isEnabled = configManager.getPlugin().getConfig().getBoolean("filters.profanity.enabled");
        if(isEnabled) {

            List<String> profanityWords = configManager.getBlacklistConfig().getStringList("profanity");

            for (String profanityWord : profanityWords) {
                if(message.contains(profanityWord)) {
                    return true;
                }
            }

        }

        return false;
    }
}
