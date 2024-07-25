package com.dragosghinea.royale.menus.config;

import com.dragosghinea.royale.internal.utils.item.ItemStackCfg;
import com.dragosghinea.royale.menus.RoyaleInventoryType;
import com.dragosghinea.royale.menus.item.MenuItemStackCfg;
import com.dragosghinea.yaml.ConfigValues;
import com.dragosghinea.yaml.annotations.Comments;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoyaleMenuCfg extends ConfigValues {

    @JsonProperty("title")
    private String title;

    @JsonProperty("inventory-type")
    @Comments({"Possible values:", "\"CHEST_1_ROW\", \"CHEST_2_ROW\", \"CHEST_3_ROW\", \"CHEST_4_ROW\", \"CHEST_5_ROW\", \"CHEST_6_ROW\", \"DISPENSER\", \"HOPPER\""})
    private RoyaleInventoryType inventoryType;

    @JsonProperty("background-item")
    private ItemStackCfg backgroundItem;

    @JsonProperty("items")
    private List<MenuItemStackCfg> items;

}
