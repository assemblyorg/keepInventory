package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.KeepInventory;

public class UtilManager {

    private final KeepInventoryState keepInventoryState;

    public UtilManager(KeepInventory plugin) {
        this.keepInventoryState = new KeepInventoryState(plugin);
    }

    public KeepInventoryState keepInventoryState() {
        return keepInventoryState;
    }

}
