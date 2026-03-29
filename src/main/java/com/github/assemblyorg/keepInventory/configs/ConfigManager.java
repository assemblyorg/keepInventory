package com.github.assemblyorg.keepInventory.configs;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class ConfigManager {

    private final MainConfig mainConfig;
    private final MessagesConfig messagesConfig;

    public ConfigManager(@NotNull JavaPlugin plugin) {
        this.mainConfig = new MainConfig(plugin, "config.yml");
        this.messagesConfig = new MessagesConfig(plugin, "messages.yml");
    }

    public @NotNull MainConfig mainConfig() {
        return mainConfig;
    }

    public @NotNull MessagesConfig messagesConfig() {
        return messagesConfig;
    }

}
