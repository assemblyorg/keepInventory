package com.github.assemblyorg.keepInventory.config;

public class MessagesConfig extends Config {

    private static final MessagesConfig INSTANCE = new MessagesConfig("messages.yml");

    private MessagesConfig(String resourcePath) {
        super(resourcePath);
    }

    public static MessagesConfig instance() {
        return INSTANCE;
    }

    public String command_reload_message() {
        String path = "command.subcommand_reload.message";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_reload_error_message() {
        String path = "command.subcommand_reload.error_message";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_error() {
        String path = "command.error";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_only_players() {
        String path = "command.only_players";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_on_message() {
        String path = "command.subcommand_on.message";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_on_message_for_sender() {
        String path = "command.subcommand_on.message_for_sender";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_on_already() {
        String path = "command.subcommand_on.already";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_on_already_for_sender() {
        String path = "command.subcommand_on.already_for_sender";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_off_message() {
        String path = "command.subcommand_off.message";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_off_message_for_sender() {
        String path = "command.subcommand_off.message_for_sender";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_off_already() {
        String path = "command.subcommand_off.already";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_off_already_for_sender() {
        String path = "command.subcommand_off.already_for_sender";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_check_message() {
        String path = "command.subcommand_check.message";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_check_message_for_sender() {
        String path = "command.subcommand_check.message_for_sender";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_check_status_enabled() {
        String path = "command.subcommand_check.status.enabled";
        return get().getString(path, getDefault().getString(path));
    }

    public String command_check_status_disabled() {
        String path = "command.subcommand_check.status.disabled";
        return get().getString(path, getDefault().getString(path));
    }

}
