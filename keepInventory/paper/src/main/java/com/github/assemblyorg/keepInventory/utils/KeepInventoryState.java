package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class KeepInventoryState {

    private final KeepInventory plugin;
    private final PersistentDataType<Byte, Boolean> type;
    private final NamespacedKey key;

    KeepInventoryState(KeepInventory plugin) {
        this.plugin = plugin;
        this.type = PersistentDataType.BOOLEAN;
        this.key = new NamespacedKey(plugin, "keepInventory");
    }

    public boolean get(Player player) {
        return player.getPersistentDataContainer().getOrDefault(key, type, false);
    }

    public void set(Player player, Boolean enabled) {
        player.getPersistentDataContainer().set(key, type, enabled);
    }

}
