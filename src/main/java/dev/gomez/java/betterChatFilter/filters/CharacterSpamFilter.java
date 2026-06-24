package dev.gomez.java.betterChatFilter.filters;

import dev.gomez.java.betterChatFilter.config.ConfigManager;

public class CharacterSpamFilter extends BaseFilter{

    private ConfigManager configManager;

    public CharacterSpamFilter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean filter(String message) {
        boolean isEnabled = configManager.getPlugin().getConfig().getBoolean("filters.character-spam.enabled");
        if(isEnabled){

            char[] chars = message.toCharArray();
            char lastChar = 0;
            int consecutiveRepeats = 1;
            int repeatLimit = configManager.getPlugin().getConfig().getInt("filters.character-spam.repeat-limit");

            for (char character : chars){
                if(character == lastChar) {
                    consecutiveRepeats++;
                    if(consecutiveRepeats >= repeatLimit){
                        return true;
                    }
                } else {
                    consecutiveRepeats = 1;
                }
                lastChar = character;
            }

        }

        return false;
    }
}
