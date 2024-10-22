package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.util.IronboundCoreEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.c446.ironbound_core.registries.AttributeRegistry.FOCUS;
import static com.c446.ironbound_core.registries.AttributeRegistry.VITALITY;
import static com.c446.ironbound_core.registries.EffectsRegistry.rgbToInt;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.*;

public class EffectRegistries {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Ironbound.MODID);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

    public static final DeferredHolder<MobEffect, MobEffect> MOONLIGHT_BLESSING = EFFECTS.register("moonlight_blessing", () -> {
        return new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(0, 175, 255));
    });
    public static final DeferredHolder<MobEffect, MobEffect> TIME_TWISTED = EFFECTS.register("time_twist", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(170, 30, 230))
                .addAttributeModifier(SPELL_RESIST, Ironbound.prefix("time_twist"), -0.2, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(COOLDOWN_REDUCTION, Ironbound.prefix("time_twist"), -0.1, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, MobEffect> MADNESS = EFFECTS.register("madness", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(120, 90, 0))
                .addAttributeModifier(ELDRITCH_SPELL_POWER, Ironbound.prefix("madness"), 0.3, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(HOLY_MAGIC_RESIST, Ironbound.prefix("madness"), -0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(HOLY_SPELL_POWER, Ironbound.prefix("madness"), -0.1, ADD_MULTIPLIED_TOTAL);
    });
    public static final DeferredHolder<MobEffect, MobEffect> FERVOR = EFFECTS.register("radiant", () -> {
        return new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(255, 90, 255))
                .addAttributeModifier(HOLY_SPELL_POWER, Ironbound.prefix("radiant"), 0.2, ADD_MULTIPLIED_BASE);

    });
    public static final DeferredHolder<MobEffect, MobEffect> HOLLOW = EFFECTS.register("hollow", () -> {
        return new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(255, 90, 255))
                .addAttributeModifier(ENDER_SPELL_POWER, Ironbound.prefix("hollow"), 0.2, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(FOCUS, Ironbound.prefix("hollow"), -0.1, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, MobEffect> FROSTED_EFFECT = EFFECTS.register("frosted", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 90, 255))
                .addAttributeModifier(SPELL_RESIST, Ironbound.prefix("frosted"), -0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(VITALITY, Ironbound.prefix("frosted"), -0.1, ADD_MULTIPLIED_BASE)
                .addAttributeModifier(ICE_SPELL_POWER, Ironbound.prefix("frosted"), 0.3, ADD_MULTIPLIED_BASE);
    });
    public static final DeferredHolder<MobEffect, MobEffect> DAMP = EFFECTS.register("wet", () -> {
        return new IronboundCoreEffect(MobEffectCategory.NEUTRAL, rgbToInt(0, 0, 125)) {
        };
    });
    public static final DeferredHolder<MobEffect, MobEffect> ROT = EFFECTS.register("rot", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(10, 70, 20))
                .withDamage(1, 20);
    });

    public static final DeferredHolder<MobEffect, MobEffect> FLAMMABLE = EFFECTS.register("flammable", () -> {
        return new IronboundCoreEffect(MobEffectCategory.HARMFUL, rgbToInt(0, 125, 0)) {
        };
    });


    //    public static final DeferredHolder<MobEffect, MobEffect> OVERCHARGED = EFFECTS.register("overcharged", () -> {
//        return new MobEffect(MobEffectCategory.HARMFUL, rgbToInt(30, 100, 255)) {
//            public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
//                this.addAttributeModifier(LIGHTNING_SPELL_POWER, Ironbound.prefix("overcharge"), getDamageBoost(pAmplifier), AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
//                this.addAttributeModifier(LIGHTNING_MAGIC_RESIST, Ironbound.prefix("overcharge"), getResReduction(pAmplifier), ADD_MULTIPLIED_BASE);
//            }
//        };
//    });
}
