package com.dragosghinea.royale.menus.marker;

import com.dragosghinea.royale.internal.utils.nbtapi.NBT;
import org.bukkit.inventory.ItemStack;

public class NMSMarker implements RoyaleItemMarker {

    private final String mark;

    public NMSMarker(String mark) {
        this.mark = mark;
    }

    @Override
    public ItemStack markItem(ItemStack item) {
        ItemStack clone = item.clone();
        NBT.modify(clone, nbtItem -> {
            nbtItem.setBoolean(mark, true);
        });

        return clone;
    }

    @Override
    public boolean isMarked(ItemStack item) {
        return NBT.get(item, nbtItem -> (boolean) nbtItem.getBoolean(mark));
    }
}
