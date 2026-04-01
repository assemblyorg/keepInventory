package com.github.assemblyorg.keepInventory.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public interface Command {

    @NotNull LiteralCommandNode<CommandSourceStack> commandNode();

    default @Nullable String description() {
        return null;
    }

    default @NotNull List<String> aliases() {
        return List.of();
    }

}
