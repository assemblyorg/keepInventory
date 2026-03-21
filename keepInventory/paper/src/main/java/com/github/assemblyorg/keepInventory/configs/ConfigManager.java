package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

    private final MessagesConfig messagesConfig;

    public ConfigManager(KeepInventory plugin) {
        this.messagesConfig = new MessagesConfig(plugin, "messages.yml");
    }

    public YamlConfiguration messagesConfig() {
        return messagesConfig.getConfig();
    }

}
