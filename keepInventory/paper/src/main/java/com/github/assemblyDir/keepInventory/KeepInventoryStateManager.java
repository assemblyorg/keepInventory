package com.github.assemblyDir.keepInventory;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public final class KeepInventoryStateManager {

    private final PersistentDataType<Byte, Boolean> type;
    private final NamespacedKey key;

    KeepInventoryStateManager(KeepInventory plugin) {
        this.type = PersistentDataType.BOOLEAN;
        this.key = new NamespacedKey(plugin, "keepInventory");
    }

    public boolean keepInventory(Player player) {
        return player.getPersistentDataContainer().getOrDefault(key, type, false);
    }

    public void keepInventory(Player player, Boolean enabled) {
        player.getPersistentDataContainer().set(key, type, enabled);
    }

}
