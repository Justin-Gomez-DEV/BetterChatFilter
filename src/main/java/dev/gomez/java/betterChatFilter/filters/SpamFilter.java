package dev.gomez.java.betterChatFilter.filters;

import dev.gomez.java.betterChatFilter.config.ConfigManager;

import java.util.HashMap;
import java.util.UUID;

public class SpamFilter {

    private HashMap<UUID, String> lastMessages = new HashMap<>();

    private ConfigManager configManager;

    public SpamFilter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public boolean filter(String message, UUID playerId){
        boolean isEnabled = configManager.getPlugin().getConfig().getBoolean("filters.spam.enabled");
        if(isEnabled){

            if(lastMessages.containsKey(playerId)){
                if(lastMessages.get(playerId).equals(message)){
                    return true;
                }
            }

                lastMessages.put(playerId, message);

        }

        return false;
    }
}
