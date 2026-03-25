package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.configs.MainConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class StateToggle {

    private final MainConfig mainConfig;
    private final PersistentDataType<Byte, Boolean> persistentDataType;
    private final NamespacedKey namespacedKey;

    StateToggle(KeepInventory plugin, MainConfig mainConfig) {
        this.mainConfig = mainConfig;
        this.persistentDataType = PersistentDataType.BOOLEAN;
        this.namespacedKey = new NamespacedKey(plugin, mainConfig.keyName());
    }

    public PersistentDataType<Byte, Boolean> dataType() {
        return persistentDataType;
    }

    public NamespacedKey namespacedKey() {
        return namespacedKey;
    }

    public boolean keepInventory(Player player) {
        return player.getPersistentDataContainer().getOrDefault(namespacedKey, persistentDataType, mainConfig.defaultKeepInventoryState());
    }

    public void keepInventory(Player player, Boolean enabled) {
        player.getPersistentDataContainer().set(namespacedKey, persistentDataType, enabled);
    }

}
