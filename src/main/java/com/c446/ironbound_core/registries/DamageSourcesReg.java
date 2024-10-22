package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.Ironbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class DamageSourcesReg {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Ironbound.prefix(name));
    }

    public static final ResourceKey<DamageType> VOID_DAMAGE = register("void_damage");
}
