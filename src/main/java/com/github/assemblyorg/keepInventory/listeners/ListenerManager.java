package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class ListenerManager {

    private final JavaPlugin plugin;
    private final List<Listener> listeners;

    public ListenerManager(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager, @NotNull UtilManager utilManager) {
        this.plugin = plugin;
        this.listeners = List.of(
            new PlayerDeath(utilManager.stateToggle()),
            new PlayerJoin(configManager.mainConfig(), utilManager.stateToggle())
        );
    }

    public void registerListeners() {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
