package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;

@Getter
public class MessageClickAction extends ClickAction {

    private final String argument;

    public MessageClickAction(String argument) {
        this(argument, null);
    }

    public MessageClickAction(String argument, ClickType clickType) {
        super((menu, event) -> {
            event.getWhoClicked().sendMessage(argument);
        }, clickType);

        this.argument = argument;
    }
}
