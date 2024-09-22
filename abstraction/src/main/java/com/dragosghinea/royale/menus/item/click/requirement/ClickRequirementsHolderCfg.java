package com.dragosghinea.royale.menus.item.click.requirement;

import com.dragosghinea.yaml.ConfigValues;
import com.dragosghinea.yaml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClickRequirementsHolderCfg extends ConfigValues {

    @JsonProperty("type")
    @Builder.Default
    protected ClickRequirementsHolderTypes holderType = ClickRequirementsHolderTypes.AND;

    @JsonProperty("requirements")
    protected List<ClickRequirementCfg> requirements;

    @JsonProperty("more-expressions")
    protected List<ClickRequirementsHolderCfg> expression;

    @JsonProperty("on-deny-actions")
    protected List<String> onDenyActions;
}
