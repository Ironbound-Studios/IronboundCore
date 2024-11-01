package org.ironbound.ironbound_core.registries;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.*;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.blood.UndyingSorcerer;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch.EldritchKnight;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch.TimeWizard;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.nature.PlagueMaster;
import org.ironbound.ironbound_core.items.ClassItem;
import org.ironbound.ironbound_core.items.GenericPotion;
import org.ironbound.ironbound_core.items.SubClassPotion;
import org.ironbound.ironbound_core.items.WizardItem;

import static org.ironbound.ironbound_core.Ironbound.MODID;

public class IBItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION1 = ITEMS.register("upgrade_potion1", () -> new GenericPotion(new Item.Properties(), 3, 1));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION2 = ITEMS.register("upgrade_potion2", () -> new GenericPotion(new Item.Properties(), 6, 2));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION3 = ITEMS.register("upgrade_potion3", () -> new GenericPotion(new Item.Properties(), 9, 3));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION4 = ITEMS.register("upgrade_potion4", () -> new GenericPotion(new Item.Properties(), 12, 4));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION5 = ITEMS.register("upgrade_potion5", () -> new GenericPotion(new Item.Properties(), 15, 5));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION6 = ITEMS.register("upgrade_potion6", () -> new GenericPotion(new Item.Properties(), 18, 6));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION7 = ITEMS.register("upgrade_potion7", () -> new GenericPotion(new Item.Properties(), 19, 7));
    public static final DeferredHolder<Item, GenericPotion> UPGRADE_POTION8 = ITEMS.register("upgrade_potion8", () -> new GenericPotion(new Item.Properties(), 20, 8));

    public static final DeferredHolder<Item, ClassItem> WIZARD_CURIO = ITEMS.register("wizard_item", () -> new WizardItem(new Item.Properties().stacksTo(1).fireResistant(), WizardClass.instance));
    public static final DeferredHolder<Item, ClassItem> WARLOCK_CURIO = ITEMS.register("warlock_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), WarlockClass.instance));
    public static final DeferredHolder<Item, ClassItem> PRIEST_CURIO = ITEMS.register("priest_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), PriestClass.instance));
    public static final DeferredHolder<Item, ClassItem> SORCERER_CURIO = ITEMS.register("sorcerer_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), SorcererClass.instance));
    public static final DeferredHolder<Item, ClassItem> FIGHTER_CURIO = ITEMS.register("fighter_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), FighterClass.instance));
    public static final DeferredHolder<Item, ClassItem> ROGUE_CURIO = ITEMS.register("rogue_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), RogueClass.instance));
    public static final DeferredHolder<Item, ClassItem> RANGER_CURIO = ITEMS.register("ranger_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), RangerClass.instance));
    public static final DeferredHolder<Item, ClassItem> HUNTER_CURIO = ITEMS.register("hunter_item", () -> new ClassItem(new Item.Properties().stacksTo(1).fireResistant(), HunterClass.instance));


    public static final DeferredHolder<Item, SubClassPotion> ABYSS_HEART = ITEMS.register("eldritch_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant(), EldritchKnight.instance));
    public static final DeferredHolder<Item, SubClassPotion> OLD_BLOOD_VIAL = ITEMS.register("blood_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant(), UndyingSorcerer.instance));
    public static final DeferredHolder<Item, SubClassPotion> ENDER_EYE = ITEMS.register("ender_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant(), TimeWizard.instance));
    public static final DeferredHolder<Item, SubClassPotion> HOLY_WATER = ITEMS.register("holy_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredHolder<Item, SubClassPotion> FIRE_HEART = ITEMS.register("fire_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredHolder<Item, SubClassPotion> ICE_HEART = ITEMS.register("ice_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredHolder<Item, SubClassPotion> ENIGMATIC_POTION = ITEMS.register("evocation_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredHolder<Item, SubClassPotion> TREE_BARK = ITEMS.register("nature_subclass_potion", () -> new SubClassPotion(new Item.Properties().stacksTo(1).fireResistant(), PlagueMaster.instance));


}
