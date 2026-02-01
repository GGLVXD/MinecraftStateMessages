package com.fryde.msm.utils;

import org.bukkit.configuration.file.FileConfiguration;

public class Embed {

    public static String buildEmbedJson(String message, String colorType, FileConfiguration config) {
        boolean embedEnabled = config.getBoolean("embed-enabled", true);
        
        if (!embedEnabled) { 
            return "{\"content\":\"" + escapeJson(message) + "\"}"; // non embed on embed disabled
        }

        // colors
        String colorConfig = colorType.equals("start") ? "embed-start-color" : "embed-stop-color";
        String colorName = config.getString(colorConfig, colorType.equals("start") ? "green" : "red");

        int color = parseColor(colorName);

        // Build embed JSON
        StringBuilder json = new StringBuilder("{\"embeds\":[{");
        
        // title
        String title = config.getString("embed-title");
        if (title != null && !title.isEmpty()) {
            json.append("\"title\":\"").append(escapeJson(title)).append("\",");
        }

        // description
        json.append("\"description\":\"").append(escapeJson(message)).append("\",");

        // color
        json.append("\"color\":").append(color);

        json.append("}]}");
        
        return json.toString();
    }

    private static int parseColor(String colorName) {
        if (colorName == null) return 0;
        
        colorName = colorName.toLowerCase().trim();
        switch (colorName) {
            case "green":
                return 3066993; // #2ECC71
            case "red":
                return 15158332; // #E74C3C
            case "blue":
                return 3447003; // #3498DB
            case "yellow":
                return 16776960; // #FFFF00
            case "orange":
                return 15105570; // #E67E22
            case "purple":
                return 10181046; // #9B59B6
            case "gray":
            case "grey":
                return 9807270; // #95A5A6
            case "black":
                return 2303786; // #23272A
            case "white":
                return 16777215; // #FFFFFF
            default:
                // parse hex
                try {
                    String hex = colorName.startsWith("#") ? colorName.substring(1) : colorName;
                    return Integer.parseInt(hex, 16);
                } catch (NumberFormatException e) {
                    return 0; // black
                }
        }
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
               .replace("\"", "\\\"")
               .replace("\n", "\\n")
               .replace("\r", "\\r")
               .replace("\t", "\\t");
    }
}
