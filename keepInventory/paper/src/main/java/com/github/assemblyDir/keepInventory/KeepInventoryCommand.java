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

    private static final String COMMAND_NAME = "keepinventory";
    private static final Collection<String> COMMAND_ALIASES = List.of("keepinv");

    public static void register(@NotNull JavaPlugin instance) {
        instance.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands ->
            commands.registrar().register(commandNode(), COMMAND_ALIASES)
        );
    }

    private static LiteralCommandNode<CommandSourceStack> commandNode() {
        var player = Commands.argument("player", ArgumentTypes.player())
                .requires(req -> req.getSender().hasPermission(KeepInventoryPermissions.Permissions.COMMAND_OTHER.get()))
                .executes(KeepInventoryCommand::execute);

        var action = Commands.argument("action", StringArgumentType.word())
                .requires(req -> req.getSender().hasPermission(KeepInventoryPermissions.Permissions.COMMAND_SELF.get()))
                .suggests((ctx, builder) -> {
                    List.of("on", "off", "check").forEach(builder::suggest);
                    return builder.buildFuture();
                })
                .executes(KeepInventoryCommand::execute);

        return Commands.literal(COMMAND_NAME)
                .then(action.then(player))
                .build();
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();

        String action = ctx.getArgument("action", String.class).toLowerCase();
        Player target;

        try {
            String lastNodeName = ctx.getNodes().getLast().getNode().getName();
            if (lastNodeName.equals("player")) {
                PlayerSelectorArgumentResolver playerSelectorArgumentResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                target = playerSelectorArgumentResolver.resolve(ctx.getSource()).getFirst();
            } else {
                if (!(sender instanceof Player)) {
                    Component message = Component
                            .text("⚠ Only players can use this subcommand")
                            .color(TextColor.color(252, 165, 3));
                    sender.getServer().getConsoleSender().sendMessage(message);
                    return Command.SINGLE_SUCCESS;
                }
                else target = (Player) sender;
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
            sender.sendMessage(message);
            sender.getServer().getConsoleSender().sendMessage(consoleMessage);
            return Command.SINGLE_SUCCESS;
        }

        PersistentDataContainer persistentDataContainer = target.getPersistentDataContainer();

        switch (action) {
            case "on": {
                if (KeepInventoryUtil.keepInventory(persistentDataContainer)) {
                    Component playerMessage = Component
                            .text("⚠ Keep inventory already enabled!")
                            .color(TextColor.color(252, 165, 3));
                    Component executorMessage = Component
                            .text("⚠ Keep inventory for " + target.getName() + " already enabled!")
                            .color(TextColor.color(252, 165, 3));
                    if (target.equals(sender)) target.sendMessage(playerMessage);
                    else sender.sendMessage(executorMessage);
                    break;
                }
                Component playerMessage = Component
                        .text("✔ Keep inventory enabled!")
                        .color(TextColor.color(140, 252, 3));
                Component executorMessage = Component
                        .text("✔ Keep inventory for " + target.getName() + " enabled!")
                        .color(TextColor.color(140, 252, 3));
                target.sendMessage(playerMessage);
                if (!target.equals(sender)) sender.sendMessage(executorMessage);

                KeepInventoryUtil.keepInventory(persistentDataContainer, true);
                new KeepInventorySwitcherEvent(target, sender, true);
                break;
            }
            case "off": {
                if (!KeepInventoryUtil.keepInventory(persistentDataContainer)) {
                    Component playerMessage = Component
                            .text("⚠ Keep inventory already disabled!")
                            .color(TextColor.color(252, 165, 3));
                    Component executorMessage = Component
                            .text("⚠ Keep inventory for " + target.getName() + " already disabled!")
                            .color(TextColor.color(252, 165, 3));
                    if (target.equals(sender)) target.sendMessage(playerMessage);
                    else sender.sendMessage(executorMessage);
                    break;
                }
                Component playerMessage = Component
                        .text("✔ Keep inventory disabled!")
                        .color(TextColor.color(140, 252, 3));
                Component executorMessage = Component
                        .text("✔ Keep inventory for " + target.getName() + " disabled!")
                        .color(TextColor.color(140, 252, 3));
                target.sendMessage(playerMessage);
                if (!target.equals(sender)) sender.sendMessage(executorMessage);

                KeepInventoryUtil.keepInventory(persistentDataContainer, false);
                new KeepInventorySwitcherEvent(target, sender, false);
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
                        .text("ℹ " + target.getName() + " keep inventory: ")
                        .color(TextColor.color(252, 165, 3))
                        .append(status);
                if (target.equals(sender)) target.sendMessage(playerMessage);
                else sender.sendMessage(executorMessage);
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
                sender.sendMessage(message);
                break;
            }
        }

        return Command.SINGLE_SUCCESS;
    }

}
