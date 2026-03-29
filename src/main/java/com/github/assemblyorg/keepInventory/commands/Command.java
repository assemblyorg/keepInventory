package com.github.assemblyorg.keepInventory.commands;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public abstract class Command {

    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    private final UtilManager utilManager;

    public Command(JavaPlugin plugin, ConfigManager configManager, UtilManager utilManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.utilManager = utilManager;
    }

    public @NotNull abstract LiteralCommandNode<CommandSourceStack> commandNode();
    public @Nullable abstract String description();
    public @NotNull abstract Collection<String> aliases();

}
