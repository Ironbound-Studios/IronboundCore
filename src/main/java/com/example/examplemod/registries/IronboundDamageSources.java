package com.example.examplemod.registries;

import com.example.examplemod.Ironbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class IronboundDamageSources {
    public static final ResourceKey<DamageType> VOID_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Ironbound.MODID, "void_damage"));
}
