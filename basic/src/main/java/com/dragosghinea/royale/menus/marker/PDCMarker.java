package com.dragosghinea.royale.menus.marker;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

// https://github.com/HelpChat/DeluxeMenus/blob/main/src/main/java/com/extendedclip/deluxemenus/dupe/marker/impl/PDCMenuItemMarker.java
public class PDCMarker implements RoyaleItemMarker{

    private final NamespacedKey mark;

    public PDCMarker(Plugin plugin, String mark) {
        this.mark = new NamespacedKey(plugin, mark);
    }

    @Override
    public ItemStack markItem(ItemStack item) {
        item = item.clone(); // To avoid modifying the original item stack

        final ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return item;
        }

        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(
                mark,
                PersistentDataType.BYTE,
                (byte) 1
        );

        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public boolean isMarked(ItemStack item) {
        final ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return false;
        }

        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(mark, PersistentDataType.BYTE);
    }
}
