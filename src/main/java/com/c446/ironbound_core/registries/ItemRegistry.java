package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.ironbound_classes.main_classes.*;
import com.c446.ironbound_core.ironbound_classes.sub_classes.eldritch.EldritchKnight;
import com.c446.ironbound_core.ironbound_classes.sub_classes.blood.UndyingSorcerer;
import com.c446.ironbound_core.items.ClassItem;
import com.c446.ironbound_core.items.GenericPotion;

import com.c446.ironbound_core.items.SubClassPotion;
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

    public static final DeferredHolder<Item, ClassItem> WIZARD_CURIO = ITEMS.register("wizard_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), WizardClass.instance));
    public static final DeferredHolder<Item, ClassItem> WARLOCK_CURIO = ITEMS.register("warlock_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), WarlockClass.instance));
    public static final DeferredHolder<Item, ClassItem> PRIEST_CURIO = ITEMS.register("priest_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), PriestClass.instance));
    public static final DeferredHolder<Item, ClassItem> SORCERER_CURIO = ITEMS.register("sorcerer_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), SorcererClass.instance));
    public static final DeferredHolder<Item, ClassItem> FIGHTER_CURIO = ITEMS.register("fighter_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), FighterClass.instance));
    public static final DeferredHolder<Item, ClassItem> ROGUE_CURIO = ITEMS.register("rogue_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), RogueClass.instance));
    public static final DeferredHolder<Item, ClassItem> RANGER_CURIO = ITEMS.register("ranger_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), RangerClass.instance));
    public static final DeferredHolder<Item, ClassItem> HUNTER_CURIO = ITEMS.register("hunter_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), HunterClass.instance));


    public static final DeferredHolder<Item, SubClassPotion> ABYSS_HEART = ITEMS.register("abyss_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant(), EldritchKnight.instance));
    public static final DeferredHolder<Item, SubClassPotion> OLD_BLOOD_VIAL = ITEMS.register("old_blood_vial", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant(), UndyingSorcerer.instance));







}
