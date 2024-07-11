package com.dragosghinea.royale.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class RoyaleMenuItem {

    public static final RoyaleMenuItem EMPTY = new RoyaleMenuItem(player -> new ItemStack(Material.AIR));

    private final Function<Player, ItemStack> itemSupplier;

    private final Function<ItemStack, ItemStack> markItem;

    private final ClickAction[] clickActions;

    public RoyaleMenuItem(Function<Player, ItemStack> itemSupplier) {
        this(itemSupplier, new ClickAction[0]);
    }

    public RoyaleMenuItem(Function<Player, ItemStack> itemSupplier, ClickAction... clickActions) {
        this(itemSupplier, item -> item, clickActions);
    }

    public RoyaleMenuItem(Function<Player, ItemStack> itemSupplier, Function<ItemStack, ItemStack> markItem, ClickAction... clickActions) {
        this.itemSupplier = itemSupplier;
        this.markItem = markItem;
        this.clickActions = clickActions;
    }

    public ClickAction[] getClickActions() {
        return clickActions;
    }

    public ItemStack getItem(Player player) {
        return itemSupplier.apply(player);
    }

    public ItemStack getItemMarked(Player player) {
        ItemStack item = getItem(player);

        return markItem.apply(item);
    }
}
