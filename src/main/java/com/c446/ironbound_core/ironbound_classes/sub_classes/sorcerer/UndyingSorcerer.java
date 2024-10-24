package com.c446.ironbound_core.ironbound_classes.sub_classes.sorcerer;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.SubClasses;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class UndyingSorcerer extends SubClasses {
    public static final UndyingSorcerer instance = new UndyingSorcerer(Ironbound.prefix("sub_class_eldritch_knight"), null);
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("undying_subclass_mana"), 25, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(BLOOD_SPELL_POWER, new AttributeModifier(Ironbound.prefix("undead_sorcerer_blood_power"), 0.025, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(HOLY_MAGIC_RESIST, new AttributeModifier(Ironbound.prefix("undead_sorcerer_holy_debuff"), -0.0125, AttributeModifier.Operation.ADD_VALUE));
    }

    public UndyingSorcerer(ResourceLocation subClassID, IBClass... parents) {
        super(subClassID, parents);
    }

    public UndyingSorcerer(IBClass parent, ResourceLocation subClassID) {
        super(parent, subClassID);
    }


}

