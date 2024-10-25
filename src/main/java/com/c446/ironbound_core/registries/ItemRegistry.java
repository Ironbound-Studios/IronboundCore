package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.ironbound_classes.main_classes.RogueClass;
import com.c446.ironbound_core.items.GenericPotion;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.c446.ironbound_core.Ironbound.MODID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

    public static final DeferredHolder<Item, GenericPotion> LEVEL_20_ROGUE = ITEMS.register("level_20_sorcerer_accessor", () ->  new GenericPotion(new Item.Properties(), 20, RogueClass.instance));
}
