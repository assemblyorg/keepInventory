package com.github.assemblyDir.keepInventory;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class KeepInventoryUtil {

    private static NamespacedKey namespacedKey;
    public static void namespacedKey(KeepInventory instance) { namespacedKey = new NamespacedKey(instance, "keepInventory"); }
    public static NamespacedKey namespacedKey() { return namespacedKey; }

    public static boolean keepInventory(PersistentDataContainer persistentDataContainer) {
        if (persistentDataContainer.has(namespacedKey()))
            return Boolean.TRUE.equals(persistentDataContainer.get(namespacedKey(), PersistentDataType.BOOLEAN));
        else return false;
    }

    public static void keepInventory(PersistentDataContainer persistentDataContainer, Boolean isEnabled) {
        persistentDataContainer.set(namespacedKey(), PersistentDataType.BOOLEAN, isEnabled);
    }

}
