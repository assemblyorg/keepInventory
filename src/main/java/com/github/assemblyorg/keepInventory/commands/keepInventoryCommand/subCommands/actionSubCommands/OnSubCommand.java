package com.github.assemblyorg.keepInventory.commands.keepInventoryCommand.subCommands.actionSubCommands;

import com.github.assemblyorg.keepInventory.commands.keepInventoryCommand.subCommands.SubCommand;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.utils.StateToggle;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import com.mojang.brigadier.builder.ArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class OnSubCommand implements SubCommand {

    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    private final StateToggle stateToggle;

    public OnSubCommand(JavaPlugin plugin, ConfigManager configManager, UtilManager utilManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.stateToggle = utilManager.stateToggle();
    }

    @Override
    public @NonNull List<ArgumentBuilder<CommandSourceStack, ?>> argument() {
        return List.of(
                Commands.literal("on")
        );
    }

}
