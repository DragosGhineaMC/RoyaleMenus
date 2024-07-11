package com.dragosghinea.royale.menus.events;

import com.dragosghinea.royale.menus.ClickAction;
import com.dragosghinea.royale.menus.RoyaleMenu;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RoyaleMenuClickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean isCancelled = false;

    private final ClickAction clickAction;

    private final RoyaleMenu menu;

    private final InventoryClickEvent event;

    public RoyaleMenuClickEvent(ClickAction clickAction, RoyaleMenu menu, InventoryClickEvent event) {
        this.clickAction = clickAction;
        this.menu = menu;
        this.event = event;
    }

    public ClickAction getClickAction() {
        return clickAction;
    }

    public InventoryClickEvent getInventoryClickEvent() {
        return event;
    }

    public RoyaleMenu getMenu() {
        return menu;
    }

    @Override
    public boolean isCancelled(){
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean arg0){
        this.isCancelled=arg0;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
