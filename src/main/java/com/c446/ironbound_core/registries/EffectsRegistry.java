package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.util.IronboundCoreEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.c446.ironbound_core.Ironbound.MODID;


public class EffectsRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MODID);
    public static final DeferredHolder<MobEffect, MobEffect> VOID_POISON = EFFECTS.register("void_poison", () -> new IronboundCoreEffect(MobEffectCategory.BENEFICIAL, rgbToInt(120,0,200)));

public static int rgbToInt(int red, int green, int blue) {
    return ((red << 16) | (green << 8) | blue);
}
}
