package dev.gomez.java.betterChatFilter.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class UpdateChecker {
    private static boolean updateAvailable;
    private static String latestVersion;

    public static void check(String currentVersion){
        try {
            URL url = new URL("https://api.spiget.org/v2/resources/136505/versions/latest");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();

            latestVersion = cleanVersion(json.get("name").getAsString());

            updateAvailable = !latestVersion.equals(cleanVersion(currentVersion));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isUpdateAvailable() {

        return updateAvailable;
    }

    public static String getLatestVersion() {

        return latestVersion;
    }

    private static String cleanVersion(String version) {
        return version.replace("v", "").trim();
    }
}
