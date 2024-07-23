package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.events.RoyaleMenuClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.BiConsumer;

public class ClickAction {

    protected final BiConsumer<RoyaleMenu, InventoryClickEvent> clickAction;

    public ClickAction(BiConsumer<RoyaleMenu, InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
    }

    public boolean click(RoyaleMenu menu, InventoryClickEvent event) {
        RoyaleMenuClickEvent clickEvent = new RoyaleMenuClickEvent(this, menu, event);
        Bukkit.getPluginManager().callEvent(clickEvent);

        if (clickEvent.isCancelled()) {
            return false;
        }

        clickAction.accept(menu, event);
        return true;
    }
}
