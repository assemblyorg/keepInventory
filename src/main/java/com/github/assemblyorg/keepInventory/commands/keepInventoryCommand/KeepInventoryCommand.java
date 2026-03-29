package com.github.assemblyorg.keepInventory.commands.keepInventoryCommand;

import com.github.assemblyorg.keepInventory.commands.Command;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import com.google.errorprone.annotations.Keep;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class KeepInventoryCommand extends Command {

    public KeepInventoryCommand(JavaPlugin plugin, ConfigManager configManager, UtilManager utilManager) {
        super(plugin, configManager, utilManager);
    }

    @Override
    public @NonNull LiteralCommandNode<CommandSourceStack> commandNode() {
        return Commands.literal("keepInventory")
                .build();
    }

    @Override
    public @Nullable String description() {
        return "";
    }

    @Override
    public @NotNull Collection<String> aliases() {
        return List.of();
    }
}
