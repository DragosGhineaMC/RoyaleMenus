package com.dragosghinea.royale.menus.item.click.requirement;

import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.dragosghinea.yaml.ConfigValues;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClickRequirementCfg extends ConfigValues {

    @JsonIgnore
    protected ClickRequirementTypes clickRequirementType;

    @JsonIgnore
    protected String argument;

    @JsonProperty("requirement")
    public String getRequirementString() {
        if (argument == null || argument.isEmpty())
            return clickRequirementType.getRequirementType();

        return clickRequirementType.getRequirementType()+ " " + argument;
    }

    @JsonProperty("requirement")
    public void setRequirementString(String requirementString) {
        String[] split = requirementString.split(" ");
        clickRequirementType = ClickRequirementTypes.fromString(split[0]);
        argument = split.length > 1 ? split[1] : null;
    }

    @JsonProperty("click-type")
    protected String clickType;

    @JsonProperty("on-deny-actions")
    protected List<String> onDenyActions;
}
