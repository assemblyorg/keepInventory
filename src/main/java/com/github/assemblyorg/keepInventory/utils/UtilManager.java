package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class UtilManager {

    private final StateToggle stateToggle;
    private final CommandContextResolver commandContextResolver;
    private final MiniMessages miniMessages;

    public UtilManager(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager) {
        this.stateToggle = new StateToggle(plugin, configManager);
        this.commandContextResolver = new CommandContextResolver();
        this.miniMessages = new MiniMessages();
    }

    public @NotNull StateToggle stateToggle() {
        return stateToggle;
    }

    public @NotNull CommandContextResolver commandContextResolver() {
        return commandContextResolver;
    }

    public @NotNull MiniMessages miniMessages() {
        return miniMessages;
    }

}
