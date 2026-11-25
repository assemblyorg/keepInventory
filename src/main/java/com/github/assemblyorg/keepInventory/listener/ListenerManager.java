package com.github.assemblyorg.keepInventory.listener;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.event.Listener;

import java.util.List;

public final class ListenerManager {

    private ListenerManager() {}

    private static final List<Listener> listeners = List.of(
            new PlayerDeathListener(),
            new PlayerJoinListener()
    );

    public static void registerListeners() {
        var plugin = KeepInventory.instance();
        for (Listener listener : listeners)
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

}
