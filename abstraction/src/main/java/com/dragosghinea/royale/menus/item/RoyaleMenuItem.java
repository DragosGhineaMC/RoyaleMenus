package com.dragosghinea.royale.menus.item;

import com.dragosghinea.royale.menus.item.click.action.ClickActionGroup;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.function.Function;

public class RoyaleMenuItem {

    public static final RoyaleMenuItem EMPTY = new RoyaleMenuItem(player -> new ItemStack(Material.AIR));

    private final Function<Player, ItemStack> itemSupplier;

    private final Function<ItemStack, ItemStack> markItem;

    @Getter
    private final ClickActionGroup clickActions;

    public RoyaleMenuItem(Function<Player, ItemStack> itemSupplier) {
        this(itemSupplier, new ClickActionGroup(Collections.emptyList()));
    }

    public RoyaleMenuItem(Function<Player, ItemStack> itemSupplier, ClickActionGroup clickActions) {
        this(itemSupplier, item -> item, clickActions);
    }

    public RoyaleMenuItem(Function<Player, ItemStack> itemSupplier, Function<ItemStack, ItemStack> markItem, ClickActionGroup clickActions) {
        this.itemSupplier = itemSupplier;
        this.markItem = markItem;
        this.clickActions = clickActions;
    }

    public ItemStack getItem(Player player) {
        return itemSupplier.apply(player);
    }

    public ItemStack getItemMarked(Player player) {
        ItemStack item = getItem(player);

        return markItem.apply(item);
    }
}
