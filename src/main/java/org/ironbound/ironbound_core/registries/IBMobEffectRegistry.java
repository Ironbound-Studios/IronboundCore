package org.ironbound.ironbound_core.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.util.DecayEffect;
import org.ironbound.ironbound_core.util.IronboundCoreEffect;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_BASE;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.FOCUS;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.VITALITY;

public class IBMobEffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Ironbound.MODID);
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> REVEL = EFFECTS.register("revel", () -> {
        return new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(170, 30, 230))
                .addAttributeModifier(ELDRITCH_SPELL_POWER, Ironbound.prefix("revel"), 0.2, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> TIME_TWISTED = EFFECTS.register("time_twist", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(170, 30, 230))
                .addAttributeModifier(SPELL_RESIST, Ironbound.prefix("time_twist"), -0.2, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(COOLDOWN_REDUCTION, Ironbound.prefix("time_twist"), -0.1, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> MADNESS = EFFECTS.register("madness", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(120, 90, 0))
                .withDamage(2, 20, IBDamageSourcesReg.MADNESS)
                .addAttributeModifier(ELDRITCH_SPELL_POWER, Ironbound.prefix("madness"), 0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(HOLY_MAGIC_RESIST, Ironbound.prefix("madness"), -0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(FOCUS, Ironbound.prefix("madness"), -0.05, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> FERVOR = EFFECTS.register("fervor", () -> {
        return new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(255, 90, 255))
                .addAttributeModifier(HOLY_SPELL_POWER, Ironbound.prefix("radiant"), 0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(ENDER_MAGIC_RESIST, Ironbound.prefix("radiant"), -0.1, ADD_MULTIPLIED_BASE);

    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> HOLLOW = EFFECTS.register("hollow", () -> {
        return new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(255, 90, 255))
                .addAttributeModifier(ENDER_SPELL_POWER, Ironbound.prefix("hollow"), 0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(HOLY_MAGIC_RESIST, Ironbound.prefix("hollow"), -0.1, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> FROSTED_EFFECT = EFFECTS.register("frozen", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 90, 255))
                .addAttributeModifier(SPELL_RESIST, Ironbound.prefix("frozen"), -0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(VITALITY, Ironbound.prefix("frozen"), -0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(ICE_SPELL_POWER, Ironbound.prefix("frozen"), 0.3, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> DAMP = EFFECTS.register("wet", () -> {
        return new IronboundCoreEffect(MobEffectCategory.NEUTRAL, rgbToInt(0, 0, 125)) {
        };
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> ROT = EFFECTS.register("decay", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(10, 70, 20))
                .withDamage(1, 20, IBDamageSourcesReg.DECAY);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> CAST_TIME_REDUCTION_EFFECT = EFFECTS.register("casting_speed_buff", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 90, 255))
                .addAttributeModifier(CAST_TIME_REDUCTION, Ironbound.prefix("cast_time_reduc"), 0.1, ADD_MULTIPLIED_TOTAL);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> COOLDOWN_REDUCTION_EFFECT = EFFECTS.register("cooldown_buff", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 90, 255))
                .addAttributeModifier(COOLDOWN_REDUCTION, Ironbound.prefix("cooldown_buff"), 0.1, ADD_MULTIPLIED_TOTAL);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> MANA_REGEN_EFFECT = EFFECTS.register("mana_regen", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 90, 255))
                .addAttributeModifier(MANA_REGEN, Ironbound.prefix("mana_regen"), 0.15, ADD_MULTIPLIED_TOTAL);
    });
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> FLAMMABLE = EFFECTS.register("flammable", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 125, 0))
                .addAttributeModifier(FIRE_MAGIC_RESIST, Ironbound.prefix("flammable"), -0.1, ADD_MULTIPLIED_TOTAL);
    });
    // Effect just for making sure entities can't receive invis if applied
    public static final DeferredHolder<MobEffect, IronboundCoreEffect> REVEALING = EFFECTS.register("revealing", () ->
    {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(227, 208, 70));
    });

    public static int rgbToInt(int red, int green, int blue) {
        return ((red << 16) | (green << 8) | blue);
    }

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }


}
