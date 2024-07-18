package com.dragosghinea.royale.menus.item.click.requirement;

import lombok.Getter;

@Getter
public enum ClickRequirementTypes {

    UNKNOWN(""),
    HAS_PERMISSION("[has_permission]"),
    HAS_MONEY("[has_money]");

    private final String requirementType;

    ClickRequirementTypes(String requirementType) {
        this.requirementType = requirementType;
    }

    public static ClickRequirementTypes fromString(String requirementType) {
        for (ClickRequirementTypes type : ClickRequirementTypes.values()) {
            if (type.getRequirementType().equalsIgnoreCase(requirementType)) {
                return type;
            }
        }

        return UNKNOWN;
    }
}
