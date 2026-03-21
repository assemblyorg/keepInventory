package com.github.assemblyorg.keepInventory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public final class KeepInventoryCommand {

    private final String COMMAND_NAME = "keepinventory";
    private final Collection<String> COMMAND_ALIASES = List.of("keepinv");

    private final KeepInventory plugin;
    private final KeepInventoryPermissionManager permissionManager;
    private final KeepInventoryStateManager stateManager;

    KeepInventoryCommand(KeepInventory plugin, KeepInventoryPermissionManager permissionManager, KeepInventoryStateManager stateManager) {
        this.plugin = plugin;
        this.permissionManager = permissionManager;
        this.stateManager = stateManager;

        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands ->
            register(commands.registrar())
        );
    }

    private void register(Commands registrar) {
        registrar.register(
            Commands.literal(COMMAND_NAME)
                .requires(sender -> sender.getSender().hasPermission(KeepInventoryPermissionManager.Permissions.COMMAND_SELF.node))
                .then(
                    Commands.argument("action", StringArgumentType.word())
                        .then(
                            Commands.argument("player", ArgumentTypes.player())
                                .requires(sender -> sender.getSender().hasPermission(KeepInventoryPermissionManager.Permissions.COMMAND_OTHER.node))
                                .executes(ctx -> execute(ctx, ctx.getArgument("player", Player.class)))
                        )
                        .suggests((ctx, builder) -> {
                            List.of("on", "off", "check").forEach(builder::suggest);
                            return builder.buildFuture();
                        })
                        .executes(ctx -> execute(ctx, null))
                )
                .build(),
            COMMAND_ALIASES
        );
    }

    private int execute(CommandContext<CommandSourceStack> ctx, Player target) {
        CommandSourceStack source = ctx.getSource();
        CommandSender sender = source.getSender();
        String action = ctx.getArgument("action", String.class).toLowerCase();

        if (target == null) {
            if (!(sender instanceof Player player)) {
                sendMessage(sender, "<#fca503>⚠ Only players can use this subcommand");
                return Command.SINGLE_SUCCESS;
            }
            target = player;
        }

        switch (action) {
            case "on" -> setKeepInventory(sender, target, true);
            case "off" -> setKeepInventory(sender, target, false);
            case "check" -> checkKeepInventory(sender, target);
            default -> sendMessage(sender, "<#fc2c03>⚠ Unrecognized argument!<newline>Use: /" + ctx.getInput().split(" ")[0] + " [on/off/check]");
        }

        return Command.SINGLE_SUCCESS;
    }

    private void setKeepInventory(CommandSender sender, Player target, boolean enable) {
        boolean currentState = stateManager.keepInventory(target);

        String status = enable ? "enabled" : "disabled";
        String action = enable ? "enable" : "disable";

        if (currentState == enable) {
            sendMessage(sender, "<#fca503>⚠ Keep inventory already " + status);
            return;
        }

        stateManager.keepInventory(target, enable);

        sendMessage(sender, "<#8cfc03>✔ Keep inventory " + getName(sender, target) + status);
        if (!sender.equals(target)) sendMessage(target, "<#8cfc03>✔ Administrator " + action + " your keep inventory");
    }

    private void checkKeepInventory(CommandSender sender, Player target) {
        boolean enabled = stateManager.keepInventory(target);
        String status = enabled ? "<#8cfc03>Enabled" : "<#fc2c03>Disabled";
        if (!sender.equals(target)) sendMessage(sender, "<#fca503>ℹ " + target.getName() + " keep inventory: " + status);
        else sendMessage(sender, "<#fca503>ℹ Keep inventory: " + status);
    }

    private void sendMessage(CommandSender receiver, String message) {
        receiver.sendMessage(MiniMessage.miniMessage().deserialize(message));
    }

    private String getName(CommandSender sender, Player target) {
        return sender.equals(target) ? "" : "for " + target.getName() + " ";
    }

}
