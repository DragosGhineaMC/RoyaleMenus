package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirementCfg;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirementsHolderCfg;
import com.dragosghinea.yaml.ConfigValues;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClickActionCfg extends ConfigValues {

    @JsonIgnore
    protected ClickActionTypes actionType;

    @JsonIgnore
    protected String argument;

    @JsonProperty("action")
    public String getClickActionString() {
        if (argument == null || argument.isEmpty())
            return actionType.getActionType();

        return actionType.getActionType() + " " + argument;
    }

    @JsonProperty("action")
    public void setClickActionString(String clickActionString) {
        String[] split = clickActionString.split(" ");
        actionType = ClickActionTypes.fromString(split[0]);
        argument = split.length > 1 ? split[1] : null;
    }

    @JsonProperty("click-type")
    protected String clickType;

    @JsonProperty("requirement")
    protected ClickRequirementCfg clickRequirement;

    @JsonProperty("requirements-expression")
    protected ClickRequirementsHolderCfg clickRequirementsExpression;
}
