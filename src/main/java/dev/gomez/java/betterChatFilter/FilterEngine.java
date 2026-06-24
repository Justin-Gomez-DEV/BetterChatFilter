package dev.gomez.java.betterChatFilter;

import dev.gomez.java.betterChatFilter.config.ConfigManager;
import dev.gomez.java.betterChatFilter.filters.*;
import dev.gomez.java.betterChatFilter.utils.MessageUtil;
import dev.gomez.java.betterChatFilter.utils.StringNormaliser;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FilterEngine {

    // Stores the config
    private ConfigManager configManager;

    // Instances
    private final ProfanityFilter profanityFilter;
    private final CapsFilter capsFilter;
    private final SpamFilter spamFilter;
    private final CharacterSpamFilter characterSpamFilter;
    private final LinkFilter linkFilter;
    private final AdvertisingFilter advertisingFilter;

    // Constructor
    public FilterEngine(ConfigManager configManager) {
        this.configManager = configManager;
        this.profanityFilter = new ProfanityFilter(configManager);
        this.capsFilter = new CapsFilter(configManager);
        this.spamFilter = new SpamFilter(configManager);
        this.characterSpamFilter = new CharacterSpamFilter(configManager);
        this.linkFilter = new LinkFilter(configManager);
        this.advertisingFilter = new AdvertisingFilter(configManager);
    }

    public boolean check(String message, UUID playerId, Player player) {
        String normalised = StringNormaliser.normalise(message);

        if (profanityFilter.filter(normalised)) {
            MessageUtil.send(player, configManager.getMessagesConfig().getString("messages.profanity"));
            return true;
        }

        if (capsFilter.filter(message)) {
            MessageUtil.send(player, configManager.getMessagesConfig().getString("messages.caps"));
            return true;
        }

        if (spamFilter.filter(normalised, playerId)) {
            MessageUtil.send(player, configManager.getMessagesConfig().getString("messages.spam"));
            return true;
        }

        if (characterSpamFilter.filter(normalised)) {
            MessageUtil.send(player, configManager.getMessagesConfig().getString("messages.character-spam"));
            return true;
        }

        if (linkFilter.filter(message)) {
            MessageUtil.send(player, configManager.getMessagesConfig().getString("messages.links"));
            return true;
        }

        if (advertisingFilter.filter(normalised)) {
            MessageUtil.send(player, configManager.getMessagesConfig().getString("messages.advertising"));
            return true;
        }

        return false;
    }
}