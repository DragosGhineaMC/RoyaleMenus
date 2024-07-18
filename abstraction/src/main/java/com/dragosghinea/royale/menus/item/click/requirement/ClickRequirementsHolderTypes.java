package com.dragosghinea.royale.menus.item.click.requirement;

import lombok.Getter;

@Getter
public enum ClickRequirementsHolderTypes {

    OR("or"),
    AND("and");

    private final String holderType;

    ClickRequirementsHolderTypes(String holderType) {
        this.holderType = holderType;
    }
}
