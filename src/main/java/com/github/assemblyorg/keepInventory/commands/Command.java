package com.github.assemblyorg.keepInventory.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;

import java.util.Collection;
import java.util.Collections;

public interface Command {

    LiteralCommandNode<CommandSourceStack> commandNode();

    default String description() {
        return "";
    }

    default Collection<String> aliases() {
        return Collections.emptyList();
    }

}
