package com.github.assemblyDir.keepInventory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public class KeepInventoryCommand {

    public static LiteralCommandNode<CommandSourceStack> commandNode() { return commandLiteral().build(); }
    public static String description() { return ""; }
    public static Collection<String> aliases() { return List.of("keepinv"); }

    private static LiteralArgumentBuilder<CommandSourceStack> commandLiteral() {
        return Commands.literal("keepinventory")
                .then(actionArgument().then(playerArgument()));
    }

    private static RequiredArgumentBuilder<CommandSourceStack, String> actionArgument() {
        return Commands.argument("action", StringArgumentType.word())
                .requires(req -> req.getSender().hasPermission("keepinventory.command.self"))
                .suggests((ctx, builder) -> {
                    List<String> suggestions = List.of("on", "off", "check");
                    for (String suggest : suggestions) builder.suggest(suggest);
                    return builder.buildFuture();
                })
                .executes(KeepInventoryCommand::execute);
    }

    private static RequiredArgumentBuilder<CommandSourceStack, PlayerSelectorArgumentResolver> playerArgument() {
        return Commands.argument("player", ArgumentTypes.player())
                .requires(req -> req.getSender().hasPermission("keepinventory.command.other"))
                .executes(KeepInventoryCommand::execute);
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        String action = ctx.getArgument("action", String.class);
        CommandSender sender = ctx.getSource().getSender();
        Player player;
        try {
            String lastNodeName = ctx.getNodes().getLast().getNode().getName();
            if ("player".equals(lastNodeName)) {
                PlayerSelectorArgumentResolver playerSelectorArgumentResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                player = playerSelectorArgumentResolver.resolve(ctx.getSource()).getFirst();
            } else {
                if (sender instanceof Player) player = (Player) sender;
                else return Command.SINGLE_SUCCESS;
            }
        } catch (CommandSyntaxException e){
            throw new RuntimeException(e);
        }

        switch (action) {
            case "on": {
                if (KeepInventoryUtil.keepInventory(player.getPersistentDataContainer())) {
                    sender.sendMessage(Component.text("⚠ Keep inventory already enabled!").color(TextColor.color(252, 165, 3)));
                    break;
                }
                KeepInventoryUtil.keepInventory(player.getPersistentDataContainer(), true);
                player.sendMessage(Component.text("✔ Keep inventory enabled!").color(TextColor.color(140, 252, 3)));
                if (!player.equals(sender)) sender.sendMessage(Component.text("✔ Keep inventory for " + player.getName() + " enabled!").color(TextColor.color(140, 252, 3)));
                break;
            }
            case "off": {
                if (!KeepInventoryUtil.keepInventory(player.getPersistentDataContainer())) {
                    sender.sendMessage(Component.text("⚠ Keep inventory already disabled!").color(TextColor.color(252, 165, 3)));
                    break;
                }
                KeepInventoryUtil.keepInventory(player.getPersistentDataContainer(), false);
                player.sendMessage(Component.text("✔ Keep inventory disabled!").color(TextColor.color(140, 252, 3)));
                if (!player.equals(sender)) sender.sendMessage(Component.text("✔ Keep inventory for " + player.getName() + " disabled!").color(TextColor.color(140, 252, 3)));
                break;
            }
            case "check": {
                Component status;
                if (KeepInventoryUtil.keepInventory(player.getPersistentDataContainer())) status = Component.text("Enabled").color(TextColor.color(140, 252, 3));
                else status = Component.text("Disabled").color(TextColor.color(252, 44, 3));

                if (player.equals(sender)) sender.sendMessage(Component.text("ℹ Keep inventory: ").color(TextColor.color(252, 165, 3)).append(status));
                else sender.sendMessage(Component.text("ℹ " + player.getName() + " keep inventory: ").color(TextColor.color(252, 165, 3)).append(status));

                break;
            }
        }
        return Command.SINGLE_SUCCESS;
    }

}
