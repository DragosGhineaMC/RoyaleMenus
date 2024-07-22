package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import org.bukkit.event.inventory.ClickType;

public class CloseClickAction extends ClickAction {

    public CloseClickAction() {
        this(null);
    }

    public CloseClickAction(ClickType clickType) {
        super((menu, event) -> {
            event.getWhoClicked().closeInventory();
        }, clickType);
    }
}
