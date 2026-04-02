package com.github.assemblyorg.keepInventory.utils;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandContextResolver {

    public List<Player> resolvePlayers(CommandContext<CommandSourceStack> ctx, String argumentName) {
        try {
            if (ctx.getNodes().stream().anyMatch(node -> node.getNode().getName().equals("player"))) {
                PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                return targetResolver.resolve(ctx.getSource());
            } else {
                if (!(ctx.getSource().getSender() instanceof Player player)) {
                    return null;
                }
                return List.of(player);
            }
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
