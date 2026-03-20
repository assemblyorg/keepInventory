package com.github.assemblyDir.keepInventory.api;

import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KeepInventoryDeathEvent extends PlayerDeathEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final boolean keepInventoryState;
    private boolean cancelled;

    public KeepInventoryDeathEvent(PlayerDeathEvent event, boolean keepInventoryState) {
        super(event.getPlayer(), event.getDamageSource(), event.getDrops(), event.getDroppedExp(), event.deathMessage(), event.getShowDeathMessages());
        this.keepInventoryState = keepInventoryState;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public boolean getKeepInventoryState() {
        return keepInventoryState;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
