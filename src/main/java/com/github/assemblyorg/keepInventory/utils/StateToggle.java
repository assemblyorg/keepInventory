package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.configs.MainConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class StateToggle {

    private final MainConfig mainConfig;
    private final PersistentDataType<Byte, Boolean> persistentDataType;
    private final NamespacedKey namespacedKey;

    public StateToggle(@NotNull KeepInventory plugin, @NotNull MainConfig mainConfig) {
        this.mainConfig = mainConfig;
        this.persistentDataType = PersistentDataType.BOOLEAN;
        this.namespacedKey = new NamespacedKey(plugin, mainConfig.keyName());
    }

    @NotNull
    public PersistentDataType<Byte, Boolean> dataType() {
        return persistentDataType;
    }

    @NotNull
    public NamespacedKey namespacedKey() {
        return namespacedKey;
    }

    public boolean keepInventory(@NotNull Player player) {
        return player.getPersistentDataContainer().getOrDefault(namespacedKey, persistentDataType, mainConfig.defaultKeepInventoryState());
    }

    public void keepInventory(@NotNull Player player, boolean enabled) {
        player.getPersistentDataContainer().set(namespacedKey, persistentDataType, enabled);
    }

}
