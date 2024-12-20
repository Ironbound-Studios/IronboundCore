package org.ironbound.ironbound_core.ironbound_classes.main_classes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;

import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.*;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.FOCUS;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.VITALITY;

public class RangerClass extends IBClass {
    public static final RangerClass instance = new RangerClass(Ironbound.prefix("ranger_class"));

    static {
        instance.addAttribute(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("class_hp"), 5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("class_mana"), 10, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(FOCUS, new AttributeModifier(Ironbound.prefix("class_focus"), 0.5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(VITALITY, new AttributeModifier(Ironbound.prefix("class_vitality"), 0.5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(COOLDOWN_REDUCTION, new AttributeModifier(Ironbound.prefix("class_cooldown_reduc"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(MANA_REGEN, new AttributeModifier(Ironbound.prefix("class_mana_regen"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(SPELL_POWER, new AttributeModifier(Ironbound.prefix("class_spell_power"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CAST_TIME_REDUCTION, new AttributeModifier(Ironbound.prefix("class_cast_time_reduc"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ATTACK_DAMAGE, new AttributeModifier(Ironbound.prefix("class_attack_damage"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ATTACK_SPEED, new AttributeModifier(Ironbound.prefix("class_attack_speed"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix("class_crit_damage"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(CRIT_CHANCE, new AttributeModifier(Ironbound.prefix("class_crit_chance"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARROW_DAMAGE, new AttributeModifier(Ironbound.prefix("class_arrow_damage"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARROW_VELOCITY, new AttributeModifier(Ironbound.prefix("class_arrow_velocity"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ARMOR_SHRED, new AttributeModifier(Ironbound.prefix("class_armor_shred"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(PROT_SHRED, new AttributeModifier(Ironbound.prefix("class_protection_shred"), 0, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }


    protected RangerClass(ResourceLocation classId) {
        super(classId);
    }

    @Override
    public ResourceLocation getResource() {
        return this.classId;
    }

}
