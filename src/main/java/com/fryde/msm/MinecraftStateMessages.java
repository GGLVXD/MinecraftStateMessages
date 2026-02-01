package com.fryde.msm;

import org.bukkit.plugin.java.JavaPlugin;
import com.fryde.msm.managers.PluginManager;
import com.fryde.msm.listeners.PlayerListener;
import com.fryde.msm.utils.Embed;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class MinecraftStateMessages extends JavaPlugin {
    private static final String DEFAULT_WEBHOOK = "https://discord.com/api/webhooks/your-webhook-url";

    @Override
    public void onEnable() {

        if (!getDataFolder().exists()) { // create config folder
            getDataFolder().mkdirs();
        }
        saveDefaultConfig();

        // Initialize managers
        PluginManager.getInstance().initialize();

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("MinecraftStateMessages has been enabled!");

        // Send start message to discord
        String webhook = getConfig().getString("discord-webhook-url", "");
        String startMessage = getConfig().getString("server-start-message", "Server has been started!");
        sendDiscordWebhookAsync(webhook, startMessage, "start");
    }

    @Override
    public void onDisable() {
        // Send stop message to discord
        String webhook = getConfig().getString("discord-webhook-url", "");
        String stopMessage = getConfig().getString("server-stop-message", "Server has been stopped!");
        sendDiscordWebhookAsync(webhook, stopMessage, "stop");

        getLogger().info("MinecraftStateMessages has been disabled!");
    }

    private void sendDiscordWebhookAsync(String url, String message, String colorType) {
        if (url == null || url.isEmpty() || DEFAULT_WEBHOOK.equals(url.trim())) {
            getLogger().warning("Discord webhook not set in config.yml. Set 'discord-webhook-url' to enable notifications.");
            return;
        }

        CompletableFuture.runAsync(() -> {
            try {
                URL webhookUrl = URI.create(url).toURL();
                HttpURLConnection conn = (HttpURLConnection) webhookUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("User-Agent", "https://fryde.org MinecraftStateMessages/1.0");

                String payload = Embed.buildEmbedJson(message, colorType, getConfig());
                byte[] out = payload.getBytes(StandardCharsets.UTF_8);
                conn.setFixedLengthStreamingMode(out.length);
                conn.connect();
                try (java.io.OutputStream os = conn.getOutputStream()) {
                    os.write(out);
                }

                int code = conn.getResponseCode();
                if (code >= 200 && code < 300) {} else {
                    getLogger().warning("Failed to send Discord webhook (http " + code + ").");
                }
                conn.disconnect();
            } catch (Exception e) {
                getLogger().warning("Error sending Discord webhook: " + e.getMessage());
            }
        });
    }

}