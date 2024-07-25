package com.dragosghinea.royale.menus.item.click.requirement;

import com.dragosghinea.yaml.ConfigValues;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClickRequirementsHolderCfg extends ConfigValues {

    @JsonProperty("type")
    protected ClickRequirementsHolderTypes holderType = ClickRequirementsHolderTypes.AND;

    @JsonProperty("requirements")
    protected List<ClickRequirementCfg> requirements;

    @JsonProperty("expression")
    protected List<ClickRequirementsHolderCfg> expression;

    @JsonProperty("on-deny-actions")
    protected List<String> onDenyActions;
}
