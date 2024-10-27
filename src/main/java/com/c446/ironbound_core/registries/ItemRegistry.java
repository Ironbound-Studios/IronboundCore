package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.items.GenericPotion;
import com.c446.ironbound_core.items.classes.WizardItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.c446.ironbound_core.Ironbound.MODID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION1 = ITEMS.register("upgrade_potion1", ()->new GenericPotion(new Item.Properties(),3));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION2 = ITEMS.register("upgrade_potion2", ()->new GenericPotion(new Item.Properties(),6));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION3 = ITEMS.register("upgrade_potion3", ()->new GenericPotion(new Item.Properties(),9));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION4 = ITEMS.register("upgrade_potion4", ()->new GenericPotion(new Item.Properties(),12));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION5 = ITEMS.register("upgrade_potion5", ()->new GenericPotion(new Item.Properties(),15));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION6 = ITEMS.register("upgrade_potion6", ()->new GenericPotion(new Item.Properties(),18));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION7 = ITEMS.register("upgrade_potion7", ()->new GenericPotion(new Item.Properties(),19));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION8 = ITEMS.register("upgrade_potion8", ()->new GenericPotion(new Item.Properties(),20));

    public static final DeferredHolder<Item, WizardItem> WIZARD_CURIO = ITEMS.register("wizard_tome", () -> new WizardItem(new Item.Properties().stacksTo(1).fireResistant()));








}
