package com.dragosghinea.royale.menus.item;

import com.dragosghinea.royale.internal.utils.item.ItemStackCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionGroupCfg;
import com.dragosghinea.yaml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
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