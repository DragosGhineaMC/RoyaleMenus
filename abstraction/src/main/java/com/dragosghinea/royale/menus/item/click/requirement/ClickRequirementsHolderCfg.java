package com.dragosghinea.royale.menus.item.click.requirement;

import com.dragosghinea.yaml.ConfigValues;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClickRequirementsHolderCfg extends ConfigValues {

    @JsonProperty("type")
    protected ClickRequirementsHolderTypes holderType = ClickRequirementsHolderTypes.AND;

    @JsonProperty("requirements")
    protected List<ClickRequirementCfg> requirements;

    @JsonProperty("expression")
    protected List<ClickRequirementsHolderCfg> expression;
}
