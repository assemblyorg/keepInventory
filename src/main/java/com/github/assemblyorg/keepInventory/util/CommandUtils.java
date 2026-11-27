package com.github.assemblyorg.keepInventory.util;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.config.MessagesConfig;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandUtils {

    private CommandUtils() {}

    private static final String errorMessageContent = "[" + CommandUtils.class.getSimpleName() +"] Error during resolving players";
    private static final Component errorMessage = Component.text(errorMessageContent);

    public static List<Player> resolvePlayers(CommandContext<CommandSourceStack> ctx, String argumentName) {
        try {
            if (ctx.getNodes().stream().anyMatch(node -> node.getNode().getName().equals(argumentName))) {
                PlayerSelectorArgumentResolver targetResolver = ctx.getArgument(argumentName, PlayerSelectorArgumentResolver.class);
                return targetResolver.resolve(ctx.getSource());
            } else {
                if (ctx.getSource().getSender() instanceof Player player) {
                    return List.of(player);
                } else {
                    return null;
                }
            }
        } catch (CommandSyntaxException e) {
            ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(
                    MessagesConfig.instance().command_error(),
                    Placeholder.unparsed("error", e.getMessage()))
            );
            KeepInventory.instance().getComponentLogger().error(errorMessage, e);
            throw new RuntimeException(errorMessageContent);
        }
    }

    public static boolean checkTargetsIsNull(CommandSender commandSender, List<Player> targets) {
        if (targets == null) {
            Component senderText = MiniMessage.miniMessage().deserialize(
                    MessagesConfig.instance().command_only_players(),
                    Placeholder.unparsed("sender", commandSender.getName())
            );
            commandSender.sendMessage(senderText);
            return true;
        } else {
            return false;
        }
    }

}
