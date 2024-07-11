package com.dragosghinea.royale.menus;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public abstract class RoyaleMenu {

    public abstract boolean openMenu(Player player);

    public abstract Inventory getInventory();

    public void triggerBackground() {
        Inventory inventory = getInventory();

        for (int i = 0; i < inventory.getSize(); i++) {
            if (getItem(i) == null) {
                setItem(i, getBackgroundItem());
            }
        }
    }

    public abstract void triggerContentUpdate();

    public RoyaleMenuItem getBackgroundItem() {
        return RoyaleMenuItem.EMPTY;
    }

    public abstract void setItem(int slot, RoyaleMenuItem item);

    public abstract RoyaleMenuItem getItem(int slot);

    public abstract Iterable<Map.Entry<Integer, RoyaleMenuItem>> getAllItems();
}
