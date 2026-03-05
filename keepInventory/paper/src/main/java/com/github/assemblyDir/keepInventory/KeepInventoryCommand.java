package com.github.assemblyDir.keepInventory;

import com.github.assemblyDir.keepInventory.api.KeepInventorySwitcherEvent;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class KeepInventoryCommand {

    public static void register(@NotNull JavaPlugin instance) {
        instance.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(command(), aliases());
        });
    }

    private static Collection<String> aliases() {
        return List.of("keepinv");
    }

    private static LiteralCommandNode<CommandSourceStack> command() {
        var player = Commands.argument("player", ArgumentTypes.player())
                .requires(req -> req.getSender().hasPermission("keepinventory.command.other"))
                .executes(KeepInventoryCommand::execute);

        var action = Commands.argument("action", StringArgumentType.word())
                .requires(req -> req.getSender().hasPermission("keepinventory.command.self"))
                .suggests((ctx, builder) -> {
                    List<String> suggestions = List.of("on", "off", "check");
                    for (String suggest : suggestions) builder.suggest(suggest);
                    return builder.buildFuture();
                })
                .executes(KeepInventoryCommand::execute);

        return Commands.literal("keepinventory")
                .then(action.then(player))
                .build();
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSender executor = ctx.getSource().getSender();

        String action = ctx.getArgument("action", String.class).toLowerCase();
        Player player;

        try {
            String lastNodeName = ctx.getNodes().getLast().getNode().getName();
            if (lastNodeName.equals("player")) {
                PlayerSelectorArgumentResolver playerSelectorArgumentResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                player = playerSelectorArgumentResolver.resolve(ctx.getSource()).getFirst();
            } else {
                if (!(executor instanceof Player)) {
                    Component message = Component
                            .text("⚠ Only players can use this subcommand")
                            .color(TextColor.color(252, 165, 3));
                    executor.getServer().getConsoleSender().sendMessage(message);
                    return Command.SINGLE_SUCCESS;
                }
                else player = (Player) executor;
            }
        } catch (CommandSyntaxException exception) {
            Component message = Component
                    .text("⚠ " + exception.getMessage())
                    .color(TextColor.color(252, 44, 3));
            Component stackTrace = Component
                    .text(Arrays.toString(exception.getStackTrace()))
                    .color(TextColor.color(252, 44, 3));
            Component consoleMessage = message
                    .appendNewline()
                    .append(stackTrace);
            executor.sendMessage(message);
            executor.getServer().getConsoleSender().sendMessage(consoleMessage);
            return Command.SINGLE_SUCCESS;
        }

        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();

        switch (action) {
            case "on": {
                if (KeepInventoryUtil.keepInventory(persistentDataContainer)) {
                    Component playerMessage = Component
                            .text("⚠ Keep inventory already enabled!")
                            .color(TextColor.color(252, 165, 3));
                    Component executorMessage = Component
                            .text("⚠ Keep inventory for " + player.getName() + " already enabled!")
                            .color(TextColor.color(252, 165, 3));
                    if (player.equals(executor)) player.sendMessage(playerMessage);
                    else executor.sendMessage(executorMessage);
                    break;
                }
                Component playerMessage = Component
                        .text("✔ Keep inventory enabled!")
                        .color(TextColor.color(140, 252, 3));
                Component executorMessage = Component
                        .text("✔ Keep inventory for " + player.getName() + " enabled!")
                        .color(TextColor.color(140, 252, 3));
                player.sendMessage(playerMessage);
                if (!player.equals(executor)) executor.sendMessage(executorMessage);

                KeepInventoryUtil.keepInventory(persistentDataContainer, true);
                new KeepInventorySwitcherEvent(player, executor, true);
                break;
            }
            case "off": {
                if (!KeepInventoryUtil.keepInventory(persistentDataContainer)) {
                    Component playerMessage = Component
                            .text("⚠ Keep inventory already disabled!")
                            .color(TextColor.color(252, 165, 3));
                    Component executorMessage = Component
                            .text("⚠ Keep inventory for " + player.getName() + " already disabled!")
                            .color(TextColor.color(252, 165, 3));
                    if (player.equals(executor)) player.sendMessage(playerMessage);
                    else executor.sendMessage(executorMessage);
                    break;
                }
                Component playerMessage = Component
                        .text("✔ Keep inventory disabled!")
                        .color(TextColor.color(140, 252, 3));
                Component executorMessage = Component
                        .text("✔ Keep inventory for " + player.getName() + " disabled!")
                        .color(TextColor.color(140, 252, 3));
                player.sendMessage(playerMessage);
                if (!player.equals(executor)) executor.sendMessage(executorMessage);

                KeepInventoryUtil.keepInventory(persistentDataContainer, false);
                new KeepInventorySwitcherEvent(player, executor, false);
                break;
            }
            case "check": {
                Component status;
                if (KeepInventoryUtil.keepInventory(persistentDataContainer)) status = Component
                        .text("Enabled")
                        .color(TextColor.color(140, 252, 3));
                else status = Component
                        .text("Disabled")
                        .color(TextColor.color(252, 44, 3));
                Component playerMessage = Component
                        .text("ℹ Keep inventory: ")
                        .color(TextColor.color(252, 165, 3))
                        .append(status);
                Component executorMessage = Component
                        .text("ℹ " + player.getName() + " keep inventory: ")
                        .color(TextColor.color(252, 165, 3))
                        .append(status);
                if (player.equals(executor)) player.sendMessage(playerMessage);
                else executor.sendMessage(executorMessage);
                break;
            }
            default: {
                Component errorMessage = Component
                        .text("⚠ Unrecognized argument!");
                Component usageMessage = Component
                        .text("Use: /" + ctx.getInput().split(" ")[0] + " [on/off/check]");
                Component message = errorMessage
                        .appendNewline()
                        .append(usageMessage)
                        .color(TextColor.color(252, 44, 3));
                executor.sendMessage(message);
                break;
            }
        }

        return Command.SINGLE_SUCCESS;
    }

}
