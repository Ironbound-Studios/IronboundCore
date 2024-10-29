package com.c446.ironbound_core.items;

import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.NoneClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class GenericPotion extends Item {
    public final int level;

    public GenericPotion(Properties properties, int level, int potionNumber) {
        super(properties.food(new FoodProperties.Builder().alwaysEdible().fast().nutrition(0).saturationModifier(0).build()));
        this.level = level;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.ironbound_core.upgrade_potion_"+(level)+".tooltip"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
