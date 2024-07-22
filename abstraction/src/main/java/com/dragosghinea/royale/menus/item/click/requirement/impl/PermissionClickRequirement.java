package com.dragosghinea.royale.menus.item.click.requirement.impl;

import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PermissionClickRequirement extends ClickRequirement {

    private final String argument;

    public PermissionClickRequirement(String argument) {
        super((menu, event) -> event.getWhoClicked().hasPermission(argument));
        this.argument = argument;
    }

    @Override
    public boolean hasRequirement(RoyaleMenu menu, InventoryClickEvent event) {
        if (!event.getWhoClicked().hasPermission(argument)) {
            return false;
        }

        return super.hasRequirement(menu, event);
    }
}
