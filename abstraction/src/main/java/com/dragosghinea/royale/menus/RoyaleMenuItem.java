package com.dragosghinea.royale.menus;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RoyaleMenuItem {

    public static final RoyaleMenuItem EMPTY = new RoyaleMenuItem(new ItemStack(Material.AIR));

    private final ItemStack item;

    private final ClickAction[] clickActions;

    public RoyaleMenuItem(ItemStack item) {
        this.item = item;
        this.clickActions = new ClickAction[0];
    }

    public RoyaleMenuItem(ItemStack item, ClickAction... clickActions) {
        this.item = item;
        this.clickActions = clickActions;
    }

    public ClickAction[] getClickActions() {
        return clickActions;
    }

    public ItemStack getItem() {
        return item;
    }

    // By default the marked item is the same as the item
    public ItemStack getMarkedItem() {
        return item;
    }
}
