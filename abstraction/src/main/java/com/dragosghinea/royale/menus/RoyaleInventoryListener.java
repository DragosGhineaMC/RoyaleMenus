package com.dragosghinea.royale.menus;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public interface RoyaleInventoryListener extends Listener {

    Inventory getInventory();

    boolean isActive();

    void close();

    void onInventoryClick(InventoryClickEvent event);

    void onInventoryClose(InventoryCloseEvent event);
}
