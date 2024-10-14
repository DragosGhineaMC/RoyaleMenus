package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.yaml.ConfigValues;
import com.dragosghinea.yaml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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
        argument = split.length > 1 ? clickActionString.substring(actionType.length()+1) : null;
    }

}
