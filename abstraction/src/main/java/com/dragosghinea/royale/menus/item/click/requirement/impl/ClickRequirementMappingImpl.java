package com.dragosghinea.royale.menus.item.click.requirement.impl;

import com.dragosghinea.royale.currencies.Currency;
import com.dragosghinea.royale.currencies.vault.VaultCurrency;
import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMapping;
import com.dragosghinea.royale.menus.item.click.action.impl.ClickActionNoRequirementsMappingImpl;
import com.dragosghinea.royale.menus.item.click.requirement.*;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ClickRequirementMappingImpl implements ClickRequirementMapping {

    private final Currency currency = new VaultCurrency("default");
    private final ClickActionMapping clickActionMapping = new ClickActionNoRequirementsMappingImpl();

    @Override
    public ClickRequirement mapFromConfig(ClickRequirementCfg clickRequirementCfg) {
        ClickRequirement clickRequirement = null;

        ClickRequirementTypes reqType = clickRequirementCfg.getClickRequirementType();

        switch (reqType) {
            case HAS_MONEY:
                clickRequirement = new MoneyClickRequirement(currency, clickRequirementCfg.getArgument());
                break;
            case HAS_PERMISSION:
                clickRequirement = new PermissionClickRequirement(clickRequirementCfg.getArgument());
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Unknown click requirement type: " + clickRequirementCfg.getClickRequirementType());
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
        ClickRequirementsHolderTypes holderType = clickRequirementsHolderCfg.getHolderType();

        List<ClickRequirement> clickRequirements = null;

        if (clickRequirementsHolderCfg.getRequirements() != null)
            clickRequirements = clickRequirementsHolderCfg.getRequirements().stream()
                    .map(this::mapFromConfig)
                    .collect(Collectors.toList());

        if (clickRequirementsHolderCfg.getExpression() != null)
            clickRequirements = clickRequirementsHolderCfg.getExpression().stream()
                    .map(this::computeFromConfigHolder)
                    .collect(Collectors.toList());

        if (clickRequirements == null)
            throw new RuntimeException("No click requirements found in the expression");

        BinaryOperator<BiPredicate<RoyaleMenu, InventoryClickEvent>> accumulator = ClickRequirementsHolderTypes.AND.equals(holderType) ? BiPredicate::and : BiPredicate::or;

        BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement = clickRequirements
                .stream()
                .map(ClickRequirement::getClickRequirement)
                .reduce(accumulator)
                .orElse((menu, event) -> true);

        return new ClickRequirement(clickRequirement);
    }
}
