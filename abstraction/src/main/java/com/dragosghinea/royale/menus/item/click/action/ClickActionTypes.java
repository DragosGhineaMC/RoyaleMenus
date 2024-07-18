package com.dragosghinea.royale.menus.item.click.action;

import lombok.Getter;

@Getter
public enum ClickActionTypes {

    UNKNOWN(""),
    MENU_CLOSE("[close]"),
    COMMAND("[command]"),
    CONSOLE_COMMAND("[console-command]"),
    MESSAGE("[message]");

    private final String actionType;

    ClickActionTypes(String actionType) {
        this.actionType = actionType;
    }

    public static ClickActionTypes fromString(String actionType) {
        for (ClickActionTypes type : ClickActionTypes.values()) {
            if (type.actionType.equalsIgnoreCase(actionType)) {
                return type;
            }
        }

        return UNKNOWN;
    }
}
