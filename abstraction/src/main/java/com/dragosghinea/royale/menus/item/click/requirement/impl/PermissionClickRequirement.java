package com.dragosghinea.royale.menus.item.click.requirement.impl;

import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.BiPredicate;

public class PermissionClickRequirement extends ClickRequirement {

    private final String argument;

    public PermissionClickRequirement(String argument, BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement) {
        this(argument, clickRequirement, null);
    }

    public PermissionClickRequirement(String argument, BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement, ClickType clickType) {
        super(clickRequirement, clickType);
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
