package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.configs.MainConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class StateToggle {

    private final MainConfig mainConfig;
    private final PersistentDataType<Byte, Boolean> persistentDataType;
    private final NamespacedKey namespacedKey;

    public StateToggle(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager) {
        this.mainConfig = configManager.mainConfig();
        this.persistentDataType = PersistentDataType.BOOLEAN;
        this.namespacedKey = new NamespacedKey(plugin, mainConfig.key_name());
    }

    public @NotNull PersistentDataType<Byte, Boolean> dataType() {
        return persistentDataType;
    }

    public @NotNull NamespacedKey namespacedKey() {
        return namespacedKey;
    }

    public boolean keepInventory(@NotNull Player player) {
        return player.getPersistentDataContainer().getOrDefault(namespacedKey, persistentDataType, mainConfig.default_keep_inventory_state());
    }

    public void keepInventory(@NotNull Player player, boolean enabled) {
        player.getPersistentDataContainer().set(namespacedKey, persistentDataType, enabled);
    }

}
