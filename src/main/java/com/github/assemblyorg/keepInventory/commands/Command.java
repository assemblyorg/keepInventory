package com.github.assemblyorg.keepInventory.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public interface Command {

    @NotNull LiteralCommandNode<CommandSourceStack> commandNode();

    default @Nullable String description() {
        return "";
    }

    default @NotNull Collection<String> aliases() {
        return Collections.emptyList();
    }

}
