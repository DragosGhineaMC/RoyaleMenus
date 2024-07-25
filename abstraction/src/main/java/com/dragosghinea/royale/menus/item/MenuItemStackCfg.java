package com.dragosghinea.royale.menus.item;

import com.dragosghinea.royale.internal.utils.item.ItemStackCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionGroupCfg;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemStackCfg extends ItemStackCfg {

    @JsonProperty("click-actions")
    private List<ClickActionGroupCfg> clickActions;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("slots")
    private List<Integer> slots;

}