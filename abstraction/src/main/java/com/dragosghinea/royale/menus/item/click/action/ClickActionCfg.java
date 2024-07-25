package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.yaml.ConfigValues;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClickActionCfg extends ConfigValues {

    @JsonIgnore
    protected String actionType;

    @JsonIgnore
    protected String argument;

    public String getClickActionString() {
        if (argument == null || argument.isEmpty())
            return actionType;

        return actionType + " " + argument;
    }

    public void setClickActionString(String clickActionString) {
        String[] split = clickActionString.split(" ");
        actionType = split[0].toUpperCase();
        argument = split.length > 1 ? split[1] : null;
    }

}
