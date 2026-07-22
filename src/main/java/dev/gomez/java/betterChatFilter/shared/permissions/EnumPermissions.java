package dev.gomez.java.betterChatFilter.shared.permissions;

public enum EnumPermissions {

    RELOAD("betterchatfilter.reload"),
    CLEAR_CHAT("betterchatfilter.clearchat"),
    CLEAR_CHAT_BYPASS("betterchatfilter.clearchat.bypass"),
    UPDATE_NOTIFY("betterchatfilter.update.notify"),
    BYPASS_FILTER("betterchatfilter.bypass");

    private final String permission;

    EnumPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}