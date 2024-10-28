package com.c446.ironbound_core.ironbound_classes.main_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static com.c446.ironbound_core.registries.AttributeRegistry.FOCUS;
import static com.c446.ironbound_core.registries.AttributeRegistry.VITALITY;
import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.*;
import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.PROT_SHRED;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class PriestClass extends IBClass {

    public static PriestClass instance = new PriestClass(Ironbound.prefix("priest_class"));

    static{
        instance.addAttribute(MAX_HEALTH, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_hp"), 5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_mana"), 25, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(FOCUS, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_focus"), 0.75, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(VITALITY, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_vitality"), 0.25, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(COOLDOWN_REDUCTION, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_cooldown_reduc"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(MANA_REGEN, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_mana_regen"), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(SPELL_POWER, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_spell_power"), 0.025/2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CAST_TIME_REDUCTION, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_cast_time_reduc"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ATTACK_DAMAGE, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_attack_damage"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ATTACK_SPEED, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_attack_speed"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_crit_damage"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CRIT_CHANCE, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_crit_chance"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARROW_DAMAGE, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_arrow_damage"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARROW_VELOCITY, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_arrow_velocity"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARMOR_SHRED, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_armor_shred"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(PROT_SHRED, new AttributeModifier(Ironbound.prefix(instance.classId.getPath() + "_protection_shred"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    protected PriestClass(ResourceLocation classId) {
        super(classId);
    }



}