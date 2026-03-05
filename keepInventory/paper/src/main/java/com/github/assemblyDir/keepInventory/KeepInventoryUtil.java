package com.github.assemblyDir.keepInventory;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public final class KeepInventoryUtil {

    private static final NamespacedKey key = KeepInventoryNamespacedKey.get();
    private static final PersistentDataType<Byte, Boolean> dataType = PersistentDataType.BOOLEAN;

    public static boolean keepInventory(PersistentDataContainer persistentDataContainer) {
        Boolean isEnabled = persistentDataContainer.get(key, dataType);
        if (isEnabled != null) return isEnabled;
        else return false;
    }

    public static void keepInventory(PersistentDataContainer persistentDataContainer, Boolean isEnabled) {
        persistentDataContainer.set(key, dataType, isEnabled);
    }

}
