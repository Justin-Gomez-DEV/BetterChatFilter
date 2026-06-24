package dev.gomez.java.betterChatFilter.filters;

import dev.gomez.java.betterChatFilter.config.ConfigManager;

public class CapsFilter extends BaseFilter{

    private ConfigManager configManager;

    public CapsFilter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean filter(String message) {
        boolean isEnabled = configManager.getPlugin().getConfig().getBoolean("filters.caps.enabled");

        if(isEnabled){

            char[] chars = message.toCharArray();

            int capsCount = 1;

            for (char character : chars) {
                if(Character.isUpperCase(character)){
                    capsCount++;
                }
            }

            double capsPercentage = (capsCount / message.length()) * 100;

            int threshold = configManager.getPlugin().getConfig().getInt("filters.caps.threshold");

            if(threshold <= capsPercentage){
                return true;
            }

        }

        return false;
    }
}
