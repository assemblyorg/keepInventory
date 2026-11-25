package com.github.assemblyorg.keepInventory.util;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.config.MainConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class StateToggleUtils {

    private StateToggleUtils() {}

    private static final PersistentDataType<Byte, Boolean> persistentDataType = PersistentDataType.BOOLEAN;
    private static final NamespacedKey namespacedKey = new NamespacedKey(KeepInventory.instance(), MainConfig.instance().key_name());

    public static @NotNull NamespacedKey namespacedKey() {
        return namespacedKey;
    }

    public static boolean keepInventory(@NotNull Player player) {
        return player.getPersistentDataContainer().getOrDefault(namespacedKey, persistentDataType, MainConfig.instance().default_keep_inventory_state());
    }

    public static void keepInventory(@NotNull Player player, boolean enabled) {
        player.getPersistentDataContainer().set(namespacedKey, persistentDataType, enabled);
    }

}
