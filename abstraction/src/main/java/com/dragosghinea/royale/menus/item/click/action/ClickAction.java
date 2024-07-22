package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.events.RoyaleMenuClickEvent;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.BiConsumer;

public class ClickAction {

    protected final BiConsumer<RoyaleMenu, InventoryClickEvent> clickAction;

    @Getter
    protected final ClickType clickType;

    @Getter
    @Setter
    protected ClickRequirement clickRequirement;

    public ClickAction(BiConsumer<RoyaleMenu, InventoryClickEvent> clickAction) {
        this(clickAction, null);
    }

    public ClickAction(BiConsumer<RoyaleMenu, InventoryClickEvent> clickAction, ClickType clickType) {
        this.clickAction = clickAction;
        this.clickType = clickType;
    }

    public boolean click(RoyaleMenu menu, InventoryClickEvent event) {
        if (this.clickType != null && !this.clickType.equals(event.getClick())) {
            return false;
        }

        RoyaleMenuClickEvent clickEvent = new RoyaleMenuClickEvent(this, menu, event);
        Bukkit.getPluginManager().callEvent(clickEvent);

        if (clickEvent.isCancelled()) {
            return false;
        }

        clickAction.accept(menu, event);
        return true;
    }
}
