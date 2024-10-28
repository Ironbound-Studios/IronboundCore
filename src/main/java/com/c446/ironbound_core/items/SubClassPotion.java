package com.c446.ironbound_core.items;

import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SubClassPotion extends Item {

    private final IBSubClass subClasses;

    public SubClassPotion(Properties properties, IBSubClass subClasses) {
        super(properties);

        this.subClasses = subClasses;
    }

    public IBSubClass getSubClasses() {
        return subClasses;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {


        return super.use(level, player, usedHand);
    }
}
