package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;

@Getter
public class ConsoleCommandClickAction extends ClickAction {

    private final String argument;

    public ConsoleCommandClickAction(String argument) {
        this(argument, null);
    }

    public ConsoleCommandClickAction(String argument, ClickType clickType) {
        super((menu, event) -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), argument);
        }, clickType);

        this.argument = argument;
    }
}
