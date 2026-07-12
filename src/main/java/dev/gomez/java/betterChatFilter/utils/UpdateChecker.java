package dev.gomez.java.betterChatFilter.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class UpdateChecker {

    private static boolean updateAvailable = false;
    private static String latestVersion = null;

    public static void check(String currentVersion, JavaPlugin plugin) {

        updateAvailable = false;

        try {
            URL url = new URL("https://api.spiget.org/v2/resources/136505/versions/latest");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();

            latestVersion = cleanVersion(json.get("name").getAsString());

            String cleanCurrentVersion = cleanVersion(currentVersion);

            updateAvailable = !latestVersion.equals(cleanCurrentVersion);

            plugin.getLogger().info("Current version: " + cleanCurrentVersion);
            plugin.getLogger().info("Latest version: " + latestVersion);

        } catch (IOException e) {
            plugin.getLogger().warning("Failed to check for updates: " + e.getMessage());
        }
    }

    public static boolean isUpdateAvailable() {
        return updateAvailable;
    }

    public static String getLatestVersion() {
        return latestVersion;
    }

    private static String cleanVersion(String version) {
        return version
                .replaceAll("[^0-9.]", "")
                .trim();
    }
}