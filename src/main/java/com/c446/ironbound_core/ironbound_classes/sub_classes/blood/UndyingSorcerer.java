package com.c446.ironbound_core.ironbound_classes.sub_classes.blood;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.SorcererClass;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class UndyingSorcerer extends IBSubClass {
    public static final UndyingSorcerer instance = new UndyingSorcerer(Ironbound.prefix("sub_class_sorcerer"), SchoolRegistry.BLOOD_RESOURCE,SorcererClass.instance);
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("undying_subclass_mana"), 25/2D, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(BLOOD_SPELL_POWER, new AttributeModifier(Ironbound.prefix("undead_sorcerer_blood_power"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(HOLY_MAGIC_RESIST, new AttributeModifier(Ironbound.prefix("undead_sorcerer_holy_debuff"), -0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public UndyingSorcerer(ResourceLocation subClassID, ResourceLocation type, IBClass... parents) {
        super(subClassID, type, parents);
    }

    public UndyingSorcerer(ResourceLocation subClassID, ResourceLocation type, IBClass parent) {
        super(subClassID, type,parent);
    }

    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }

}

