package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;

@Getter
public class CommandClickAction extends ClickAction {

    private final String argument;

    public CommandClickAction(String argument) {
        this(argument, null);
    }

    public CommandClickAction(String argument, ClickType clickType) {
        super((menu, event) -> {
            Bukkit.dispatchCommand(event.getWhoClicked(), argument);
        }, clickType);

        this.argument = argument;
    }
}
