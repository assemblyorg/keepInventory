package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.jetbrains.annotations.NotNull;

public final class ConfigManager {

    private final MainConfig mainConfig;
    private final MessagesConfig messagesConfig;

    public ConfigManager(@NotNull KeepInventory plugin) {
        this.mainConfig = new MainConfig(plugin, "config.yml");
        this.messagesConfig = new MessagesConfig(plugin, "messages.yml");
    }

    @NotNull
    public MainConfig mainConfig() {
        return mainConfig;
    }

    @NotNull
    public MessagesConfig messagesConfig() {
        return messagesConfig;
    }

}
