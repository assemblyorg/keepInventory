package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.utils.KeepInventoryState;
import org.bukkit.event.Listener;

public class ListenerManager {

    private final KeepInventory plugin;
    private final Listener[] listeners;

    public ListenerManager(KeepInventory plugin, KeepInventoryState keepInventoryState) {
        this.plugin = plugin;
        this.listeners = new Listener[]{
                new PlayerDeath(keepInventoryState)
        };
    }

    public void registerListeners() {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
