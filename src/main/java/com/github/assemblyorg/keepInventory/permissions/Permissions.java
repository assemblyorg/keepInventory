package com.github.assemblyorg.keepInventory.permissions;

public enum Permissions {

    COMMAND_SELF("keepinventory.command.self"),
    COMMAND_OTHER("keepinventory.command.other");

    private final String id;

    Permissions(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
