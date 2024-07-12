package com.dragosghinea.royale.menus;

import org.bukkit.event.inventory.InventoryType;

public enum RoyaleInventoryType {
    CHEST_1_ROW(InventoryType.CHEST, 9),
    CHEST_2_ROW(InventoryType.CHEST, 18),
    CHEST_3_ROW(InventoryType.CHEST, 27),
    CHEST_4_ROW(InventoryType.CHEST, 36),
    CHEST_5_ROW(InventoryType.CHEST, 45),
    CHEST_6_ROW(InventoryType.CHEST, 54),
    DISPENSER(InventoryType.DISPENSER, 9),
    HOPPER(InventoryType.HOPPER, 5);

    private final InventoryType inventoryType;
    private final int size;

    RoyaleInventoryType(InventoryType inventoryType, int size) {
        this.inventoryType = inventoryType;
        this.size = size;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public int getSize() {
        return size;
    }
}
