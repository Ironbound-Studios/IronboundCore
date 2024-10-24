package com.c446.ironbound_core.util;


import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IronboundCoreEffect extends MobEffect {
    public final List<ItemStack> curativeItems;

    static{

    }
    protected double tickDamage=0;
    protected int interval=0;
    protected ResourceKey<DamageType> type = null;

    public IronboundCoreEffect withDamage(double damagePerLevel, int tickInterval, ResourceKey<DamageType> type){
        var newEffect = new IronboundCoreEffect(this.getCategory(), this.getColor());
        newEffect.tickDamage = damagePerLevel;
        newEffect.interval = tickInterval;
        newEffect.type = type;
        return newEffect;
    }

    public IronboundCoreEffect(MobEffectCategory effectCategory, int color) {
        this(effectCategory, color, List.of());
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (this.tickDamage != 0 && livingEntity.tickCount % 20 == 0){
            DamageSources.applyDamage(livingEntity, (float) this.tickDamage, new DamageSource(livingEntity.level().registryAccess().holderOrThrow(type)));
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    public IronboundCoreEffect(MobEffectCategory type, int color, List<ItemStack> curativeItems) {
        super(type, color);
        this.curativeItems = curativeItems;
    }

}