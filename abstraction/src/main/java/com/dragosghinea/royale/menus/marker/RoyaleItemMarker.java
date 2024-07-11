package com.dragosghinea.royale.menus.marker;

import org.bukkit.inventory.ItemStack;

public interface RoyaleItemMarker {

    ItemStack markItem(ItemStack item);

    boolean isMarked(ItemStack item);
}
