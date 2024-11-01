package org.ironbound.ironbound_core.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GenericPotion extends Item {
    public final int level;
    public final int potionNumber;

    public GenericPotion(Properties properties, int level, int potionNumber) {
        super(properties.food(new FoodProperties.Builder().alwaysEdible().fast().nutrition(0).saturationModifier(0).build()));
        this.level = level;
        this.potionNumber = potionNumber;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.ironbound_core.upgrade_potion_" + (potionNumber) + ".tooltip"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
