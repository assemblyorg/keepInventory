package com.github.assemblyDir.keepInventory;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class KeepInventoryNamespacedKey {

    private static NamespacedKey namespacedKey;

    public static void register(@NotNull JavaPlugin instance) {
        KeepInventoryNamespacedKey.namespacedKey = new NamespacedKey(instance, "keepInventory");
    }

    public static NamespacedKey get() {
        return namespacedKey;
    }

}
