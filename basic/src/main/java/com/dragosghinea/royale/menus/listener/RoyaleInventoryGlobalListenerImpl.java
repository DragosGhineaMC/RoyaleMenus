package com.dragosghinea.royale.menus.listener;

import com.dragosghinea.royale.menus.GlobalRoyaleMenuImpl;
import com.dragosghinea.royale.menus.RoyaleInventoryListener;
import com.dragosghinea.royale.menus.RoyaleMenuItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class RoyaleInventoryGlobalListenerImpl implements RoyaleInventoryListener {

    private final GlobalRoyaleMenuImpl globalRoyaleMenu;

    private boolean active = true;

    public RoyaleInventoryGlobalListenerImpl(Plugin plugin, GlobalRoyaleMenuImpl globalRoyaleMenu) {
        this.globalRoyaleMenu = globalRoyaleMenu;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public Inventory getInventory() {
        return globalRoyaleMenu.getInventory();
    }

    @Override
    public synchronized void onInventoryClick(InventoryClickEvent event) {
        RoyaleMenuItem item = globalRoyaleMenu.getItem(event.getSlot());
        if (item == null)
            return;

        Arrays.stream(item.getClickActions())
                .forEach(clickAction -> clickAction.click(globalRoyaleMenu, event));
    }

    @Override
    public synchronized void onInventoryClose(InventoryCloseEvent event) {
        globalRoyaleMenu.cleanup();
    }

    @EventHandler
    public void onInventoryClickHandler(InventoryClickEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!getInventory().equals(event.getInventory())) {
            return;
        }

        event.setCancelled(true);

        if (event.getSlot() < 0 || event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        onInventoryClick(event);
    }

    @EventHandler
    public void onInventoryCloseHandler(InventoryCloseEvent event) {
        if (!getInventory().equals(event.getInventory())) {
            return;
        }

        onInventoryClose(event);
        close();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void close() {
        if (!isActive()) {
            return;
        }

        HandlerList.unregisterAll(this);
        active = false;
    }
}
