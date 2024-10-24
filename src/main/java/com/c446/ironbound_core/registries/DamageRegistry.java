package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.Ironbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DamageRegistry {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Ironbound.prefix(name));
    }

    public static final ResourceKey<DamageType> MADNESS = register("madness_damage");

    static{

    }

}
