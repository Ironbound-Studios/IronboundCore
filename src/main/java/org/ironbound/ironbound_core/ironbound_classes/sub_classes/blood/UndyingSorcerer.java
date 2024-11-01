package org.ironbound.ironbound_core.ironbound_classes.sub_classes.blood;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.IBSubClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.SorcererClass;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class UndyingSorcerer extends IBSubClass {
    public static final UndyingSorcerer instance = new UndyingSorcerer(Ironbound.prefix("sub_class_sorcerer"), SchoolRegistry.BLOOD_RESOURCE, SorcererClass.instance);

    static {
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("undying_subclass_mana"), 12.5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(BLOOD_SPELL_POWER, new AttributeModifier(Ironbound.prefix("undead_sorcerer_blood_power"), 0.00625 * 1 / 3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(HOLY_MAGIC_RESIST, new AttributeModifier(Ironbound.prefix("undead_sorcerer_holy_debuff"), -0.00625, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public UndyingSorcerer(ResourceLocation subClassID, ResourceLocation type, IBClass... parents) {
        super(subClassID, type, parents);
    }

    public UndyingSorcerer(ResourceLocation subClassID, ResourceLocation type, IBClass parent) {
        super(subClassID, type, parent);
    }

    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }

}

