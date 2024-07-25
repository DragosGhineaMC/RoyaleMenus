package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirementCfg;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirementsHolderCfg;
import com.dragosghinea.yaml.ConfigValues;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClickActionGroupCfg extends ConfigValues {
    @JsonProperty("click-type")
    protected ClickType clickType;

    @JsonProperty("actions")
    protected List<String> actions;

    @JsonProperty("requirement")
    protected ClickRequirementCfg clickRequirement;

    @JsonProperty("requirements-expression")
    protected ClickRequirementsHolderCfg clickRequirementsExpression;
}
