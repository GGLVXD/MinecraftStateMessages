# MinecraftStateMessages

<div align="center">

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Minecraft](https://img.shields.io/badge/minecraft-1.20+-green.svg)
[![Discord](https://img.shields.io/badge/support-discord-7289da.svg)](https://dsc.gg/gglvxd)

A powerful Minecraft server plugin that sends real-time server status notifications to Discord via webhooks with customizable embeds.

[Features](#-features) • [Installation](#-installation) • [Configuration](#-configuration) • [Support](#-support)

---

</div>

## 📋 Features

- **🔔 Real-time Notifications** - Instant Discord notifications when your server starts or stops
- **🎨 Customizable Embeds** - Beautiful, fully customizable Discord embeds with color coding
- **🔧 Flexible Configuration** - Easy-to-use YAML configuration with sensible defaults
- **🎯 Lightweight** - Minimal performance impact on your server

## 📦 Installation

1. Download the latest `MinecraftStateMessages.jar` from the [releases page](https://github.com/GGLVXD/MinecraftStateMessages/releases)
2. Place the JAR file in your server's `plugins/` directory
3. Restart your server
4. Configure the plugin by editing `plugins/MinecraftStateMessages/config.yml`
5. Run `/reload` or restart the server to apply changes

## ⚙️ Configuration

After the first run, a `config.yml` file will be generated in `plugins/MinecraftStateMessages/`. Here's a detailed breakdown of all configuration options:

### Basic Settings

```yaml
# Message sent to Discord when the server starts
server-start-message: "Server has been started!"

# Message sent to Discord when the server stops
server-stop-message: "Server has been stopped!"

# Your Discord webhook URL - get this from Discord Server Settings > Integrations > Webhooks
discord-webhook-url: "https://discord.com/api/webhooks/your-webhook-url"
```

### Embed Settings

```yaml
# Enable or disable Discord embeds (if false, sends plain text messages)
embed-enabled: true

# Color for server start notifications
# Supported: green, red, blue, yellow, orange, purple, gray/grey, black, white
# Or use hex codes: "#2ECC71" or "2ECC71"
embed-start-color: "green"

# Color for server stop notifications
embed-stop-color: "red"

# Title displayed at the top of the embed
# Leave empty ("") to hide the title completely
embed-title: "Minecraft Server"
```

#### 🎨 Color Reference

| Color Name | Hex Code | Preview |
|------------|----------|---------|
| `green` | `#2ECC71` | 🟢 Perfect for start messages |
| `red` | `#E74C3C` | 🔴 Perfect for stop messages |
| `blue` | `#3498DB` | 🔵 General information |
| `yellow` | `#FFFF00` | 🟡 Warnings |
| `orange` | `#E67E22` | 🟠 Alerts |
| `purple` | `#9B59B6` | 🟣 Special events |
| `gray`/`grey` | `#95A5A6` | ⚪ Neutral |
| `black` | `#23272A` | ⚫ Discord dark theme |
| `white` | `#FFFFFF` | ⚪ Bright |

You can also use any custom hex color:
```yaml
embed-start-color: "#FF6B6B"  # Custom coral red
embed-stop-color: "4ECDC4"    # Custom turquoise (# is optional)
```

### Configuration Examples

#### Example 1: Minimal Setup (No Title)
```yaml
server-start-message: "✅ Server Online"
server-stop-message: "❌ Server Offline"
discord-webhook-url: "https://discord.com/api/webhooks/123456789/abcdefg"
embed-enabled: true
embed-start-color: "green"
embed-stop-color: "red"
embed-title: ""  # Empty = no title shown
```

#### Example 2: Branded Server
```yaml
server-start-message: "🎮 The server is now online and ready for players!"
server-stop-message: "🛑 The server is now offline for maintenance."
discord-webhook-url: "https://discord.com/api/webhooks/123456789/abcdefg"
embed-enabled: true
embed-start-color: "#00FF00"
embed-stop-color: "#FF0000"
embed-title: "🌟 AwesomeCraft Network"
```

#### Example 3: Simple Text Messages (No Embeds)
```yaml
server-start-message: "[SERVER] Started successfully!"
server-stop-message: "[SERVER] Shutting down..."
discord-webhook-url: "https://discord.com/api/webhooks/123456789/abcdefg"
embed-enabled: false  # Sends plain text instead of embeds
embed-start-color: "green"
embed-stop-color: "red"
embed-title: ""
```

## 🔗 Setting Up Discord Webhook

1. Open your Discord server
2. Go to **Server Settings** → **Integrations** → **Webhooks**
3. Click **New Webhook** or **Create Webhook**
4. Customize the webhook name and channel
5. Click **Copy Webhook URL**
6. Paste the URL into `discord-webhook-url` in your config.yml

## 🛠️ Advanced Usage

### Title Visibility

The `embed-title` field has special behavior:
- **Set to any text**: Displays that text as the embed title
  ```yaml
  embed-title: "My Awesome Server"
  ```
- **Set to empty string (`""`))**: No title will be shown
  ```yaml
  embed-title: ""
  ```
- **Not specified/removed**: No title will be shown (same as empty)

### Multiple Webhooks (Advanced)

Want to send notifications to multiple Discord channels? You can modify the webhook URL dynamically or create multiple configurations. For advanced setups, consider forking this plugin and adding multi-webhook support.

## 🔍 Troubleshooting

### Webhook not sending

**Problem**: No messages appear in Discord

**Solutions**:
- ✅ Verify your webhook URL is correct
- ✅ Ensure `discord-webhook-url` is not set to the default placeholder
- ✅ Check server console for error messages
- ✅ Confirm the webhook hasn't been deleted in Discord
- ✅ Test the webhook URL using a tool like [webhook.site](https://webhook.site)

### Colors not working

**Problem**: Embeds show wrong colors or no color

**Solutions**:
- ✅ Use supported color names (see [Color Reference](#-color-reference))
- ✅ If using hex codes, ensure format is correct: `"#FF0000"` or `"FF0000"`
- ✅ Reload the plugin after changing colors

### Title showing when it shouldn't

**Problem**: Title appears even when set to empty

**Solutions**:
- ✅ Ensure `embed-title: ""` is exactly like this (empty quotes)
- ✅ Remove any spaces between quotes: ❌ `" "` → ✅ `""`
- ✅ Reload/restart server after config changes

## 🤝 Support & Links

<div align="center">

### Get Help & Stay Connected

[![Discord Support](https://img.shields.io/badge/Support-Join%20Discord-7289da?style=for-the-badge&logo=discord&logoColor=white)](https://dsc.gg/gglvxd)
[![Developer](https://img.shields.io/badge/Developer-gglvxd-00ADD8?style=for-the-badge&logo=dev.to&logoColor=white)](https://gglvxd.net)
[![Fryde](https://img.shields.io/badge/Powered%20by-Fryde-FF6B6B?style=for-the-badge)](https://fryde.org)

</div>

- **💬 Support Server**: [dsc.gg/gglvxd](https://dsc.gg/gglvxd) - Get help, report bugs, or suggest features
- **👨‍💻 Developer**: [gglvxd.net](https://gglvxd.net) - Visit the developer's website
- **🏢 Organization**: [Fryde](https://fryde.org) - Powered by Fryde

## 📝 Permissions

This plugin requires no special permissions. It only needs:
- File system access for config management
- Network access for webhook requests

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Credits

Developed with ❤️ by [gglvxd](https://gglvxd.net)

---

<div align="center">

**Made with 💜 by [gglvxd](https://gglvxd.net) | Powered by [Fryde](https://fryde.org)**

*If you like this plugin, consider giving it a ⭐ and joining our [Discord](https://dsc.gg/gglvxd)!*

</div>
