package com.c446.ironbound_core.ironbound_classes.main_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static com.c446.ironbound_core.registries.IBAttributeRegistry.FOCUS;
import static com.c446.ironbound_core.registries.IBAttributeRegistry.VITALITY;
import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.*;
import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.PROT_SHRED;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class RogueClass extends IBClass {
    public static final RogueClass instance = new RogueClass(Ironbound.prefix("rogue_class"));

    protected RogueClass(ResourceLocation classId) {
        super(classId);
    }

    @Override
    public ResourceLocation getResource() {
        return this.classId;

    }

    static {
        instance.addAttribute(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("class_hp"), 5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("class_mana"), 10, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(FOCUS, new AttributeModifier(Ironbound.prefix("class_focus"), 0.25, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(VITALITY, new AttributeModifier(Ironbound.prefix("class_vitality"), 0.75, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(COOLDOWN_REDUCTION, new AttributeModifier(Ironbound.prefix("class_cooldown_reduc"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(MANA_REGEN, new AttributeModifier(Ironbound.prefix("class_mana_regen"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(SPELL_POWER, new AttributeModifier(Ironbound.prefix("class_spell_power"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CAST_TIME_REDUCTION, new AttributeModifier(Ironbound.prefix("class_cast_time_reduc"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ATTACK_DAMAGE, new AttributeModifier(Ironbound.prefix("class_attack_damage"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ATTACK_SPEED, new AttributeModifier(Ironbound.prefix("class_attack_speed"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix("class_crit_damage"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CRIT_CHANCE, new AttributeModifier(Ironbound.prefix("class_crit_chance"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARROW_DAMAGE, new AttributeModifier(Ironbound.prefix("class_arrow_damage"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARROW_VELOCITY, new AttributeModifier(Ironbound.prefix("class_arrow_velocity"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARMOR_SHRED, new AttributeModifier(Ironbound.prefix("class_armor_shred"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(PROT_SHRED, new AttributeModifier(Ironbound.prefix("class_protection_shred"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}
