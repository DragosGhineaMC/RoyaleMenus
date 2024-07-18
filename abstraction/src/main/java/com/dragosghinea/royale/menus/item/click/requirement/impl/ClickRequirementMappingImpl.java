package com.dragosghinea.royale.menus.item.click.requirement.impl;

import com.dragosghinea.royale.currencies.Currency;
import com.dragosghinea.royale.currencies.vault.VaultCurrency;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMapping;
import com.dragosghinea.royale.menus.item.click.action.impl.ClickActionNoRequirementsMappingImpl;
import com.dragosghinea.royale.menus.item.click.requirement.*;
import org.bukkit.event.inventory.ClickType;

import java.util.List;
import java.util.stream.Collectors;

public class ClickRequirementMappingImpl implements ClickRequirementMapping {

    private final Currency currency = new VaultCurrency("default");
    private final ClickActionMapping clickActionMapping = new ClickActionNoRequirementsMappingImpl();

    @Override
    public ClickRequirement mapFromConfig(ClickRequirementCfg clickRequirementCfg) {
        ClickRequirement clickRequirement = null;

        ClickRequirementTypes reqType = ClickRequirementTypes.fromString(clickRequirementCfg.getClickType());

        ClickType clickType = clickRequirementCfg.getClickRequirementType() == null ? null : ClickType.valueOf(clickRequirementCfg.getClickType().toUpperCase());

        switch (reqType) {
            case HAS_MONEY:
                clickRequirement = new MoneyClickRequirement(currency, clickRequirementCfg.getArgument(), clickType);
                break;
            case HAS_PERMISSION:
                clickRequirement = new PermissionClickRequirement(clickRequirementCfg.getArgument(), clickType);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Unknown click requirement type: " + clickRequirementCfg.getClickType());
        }

        List<ClickAction> onDenyActions = clickRequirementCfg.getOnDenyActions().stream()
                .map(str -> {
                    ClickActionCfg clickActionCfg = new ClickActionCfg();
                    clickActionCfg.setClickActionString(str);

                    return clickActionCfg;
                })
                .map(clickActionMapping::mapFromConfig)
                .collect(Collectors.toList());

        clickRequirement.setOnDenyActions(onDenyActions);

        return clickRequirement;
    }

    @Override
    public ClickRequirement computeFromConfigHolder(ClickRequirementsHolderCfg clickRequirementsHolderCfg) {
        return null;
    }
}
