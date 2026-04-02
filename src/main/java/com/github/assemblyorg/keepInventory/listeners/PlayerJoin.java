package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.configs.MainConfig;
import com.github.assemblyorg.keepInventory.utils.StateToggle;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class PlayerJoin implements Listener {

    private final MainConfig mainConfig;
    private final StateToggle stateToggle;

    public PlayerJoin(@NotNull ConfigManager configManager, @NotNull UtilManager utilManager) {
        this.mainConfig = configManager.mainConfig();
        this.stateToggle = utilManager.stateToggle();
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();

        boolean hasState = persistentDataContainer.has(stateToggle.namespacedKey());
        if (!hasState) stateToggle.keepInventory(player, mainConfig.default_keep_inventory_state());
    }

}
