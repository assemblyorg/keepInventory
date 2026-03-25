package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.configs.MainConfig;
import com.github.assemblyorg.keepInventory.utils.StateToggle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;

public class PlayerJoin implements Listener {

    private final MainConfig mainConfig;
    private final StateToggle stateToggle;

    public PlayerJoin(MainConfig mainConfig, StateToggle stateToggle) {
        this.mainConfig = mainConfig;
        this.stateToggle = stateToggle;
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        boolean hasState = persistentDataContainer.has(stateToggle.namespacedKey());

        if (!hasState) stateToggle.keepInventory(player, mainConfig.defaultKeepInventoryState());
    }

}
