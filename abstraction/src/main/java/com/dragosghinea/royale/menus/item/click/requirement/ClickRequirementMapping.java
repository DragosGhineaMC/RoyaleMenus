package com.dragosghinea.royale.menus.item.click.requirement;

public interface ClickRequirementMapping {

    ClickRequirement mapFromConfig(ClickRequirementCfg clickRequirementCfg);

    ClickRequirement computeFromConfigHolder(ClickRequirementsHolderCfg clickRequirementsHolderCfg);
}
