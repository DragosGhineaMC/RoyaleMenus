package com.dragosghinea.royale.menus.config;

import com.dragosghinea.royale.internal.utils.item.CfgToItemStackMapper;
import com.dragosghinea.royale.internal.utils.item.StandardCfgToItemStackMapper;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.menus.item.MenuItemStackCfg;
import com.dragosghinea.royale.menus.item.RoyaleMenuItem;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionGroup;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMapping;
import com.dragosghinea.royale.menus.item.click.action.impl.ClickActionMappingImpl;
import com.dragosghinea.royale.menus.marker.MarkerManager;
import com.dragosghinea.royale.menus.marker.RoyaleItemMarker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RoyaleMenuItemMapperImpl implements RoyaleMenuItemMapper {

    private final CfgToItemStackMapper itemStackMapper = new StandardCfgToItemStackMapper();
    private final ClickActionMapping clickActionMapping;
    private final RoyaleItemMarker itemMarker;
    private final StringMessageProcessorChain messageProcessorChain;

    private ItemStack applyMessageProcessorChain(Player player, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return itemStack;
        }

        if (itemMeta.hasDisplayName()) {
            String displayName = itemMeta.getDisplayName();
            itemMeta.setDisplayName(messageProcessorChain.processMessage(player, displayName));
        }

        if (itemMeta.hasLore() && itemMeta.getLore() != null) {
            List<String> lore = itemMeta.getLore();
            itemMeta.setLore(lore.stream().map(loreLine -> messageProcessorChain.processMessage(player, loreLine)).collect(Collectors.toList()));
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public RoyaleMenuItemMapperImpl(Plugin plugin, StringMessageProcessorChain messageProcessorChain) {
        this.itemMarker = MarkerManager.getAvailableMarker(plugin);
        this.messageProcessorChain = messageProcessorChain;
        this.clickActionMapping = new ClickActionMappingImpl();
    }

    public RoyaleMenuItemMapperImpl(RoyaleItemMarker itemMarker, StringMessageProcessorChain messageProcessorChain, Map<String, Function<String, ClickAction>> extraClickActionMappings) {
        this.itemMarker = itemMarker;
        this.messageProcessorChain = messageProcessorChain;
        this.clickActionMapping = new ClickActionMappingImpl(extraClickActionMappings);
    }

    @Override
    public RoyaleMenuItem mapFromConfig(MenuItemStackCfg cfg) {
        ItemStack itemStack = itemStackMapper.mapItemStack(cfg);

        List<ClickActionGroup> clickActions = cfg.getClickActions() == null ? Collections.emptyList() : cfg.getClickActions().stream().map(clickActionMapping::mapFromConfig).collect(Collectors.toList());

        return new RoyaleMenuItem(player -> applyMessageProcessorChain(player, itemStack), itemMarker::markItem, clickActions);
    }
}
