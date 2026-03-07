package com.github.assemblyDir.keepInventory;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public final class KeepInventoryUtil {

    private static final PersistentDataType<Byte, Boolean> PERSISTENT_DATA_TYPE = PersistentDataType.BOOLEAN;

    public static boolean keepInventory(PersistentDataContainer persistentDataContainer) {
        return persistentDataContainer.getOrDefault(KeepInventoryNamespacedKey.get(), PERSISTENT_DATA_TYPE, false);
    }

    public static void keepInventory(PersistentDataContainer persistentDataContainer, Boolean isEnabled) {
        persistentDataContainer.set(KeepInventoryNamespacedKey.get(), PERSISTENT_DATA_TYPE, isEnabled);
    }

}
