package com.c446.ironbound_core.items;

import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.NoneClass;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.Supplier;

public class GenericPotion extends Item {
    public int level = 0;
    public IBClass ibClass = NoneClass.instance;

    public GenericPotion(Properties properties, int lvl, IBClass ibClass) {
        super(properties.food(new FoodProperties.Builder().alwaysEdible().fast().nutrition(0).saturationModifier(0).build()));
        this.ibClass = ibClass;
        level = lvl;
    }
}
