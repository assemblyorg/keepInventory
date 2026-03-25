package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;

public final class ConfigManager {

    private final MainConfig mainConfig;
    private final MessagesConfig messagesConfig;

    public ConfigManager(KeepInventory plugin) {
        this.mainConfig = new MainConfig(plugin, "config.yml");
        this.messagesConfig = new MessagesConfig(plugin, "messages.yml");
    }

    public MainConfig mainConfig() {
        return mainConfig;
    }

    public MessagesConfig messagesConfig() {
        return messagesConfig;
    }

}
