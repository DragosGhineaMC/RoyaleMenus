package com.dragosghinea.royale.menus.item;

import com.dragosghinea.royale.internal.utils.item.ItemStackCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuItemStackCfg extends ItemStackCfg {

    @JsonProperty("click-actions")
    private List<ClickActionCfg> clickActions;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("slots")
    private List<Integer> slots;
}