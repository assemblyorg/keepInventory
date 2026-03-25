package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public final class ListenerManager {

    private final KeepInventory plugin;
    private final List<Listener> listeners = new ArrayList<>();

    public ListenerManager(KeepInventory plugin, ConfigManager configManager, UtilManager utilManager) {
        this.plugin = plugin;

        listeners.add(new PlayerDeath(utilManager.stateToggle()));
        listeners.add(new PlayerJoin(configManager.mainConfig(), utilManager.stateToggle()));
    }

    public void registerListeners() {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
