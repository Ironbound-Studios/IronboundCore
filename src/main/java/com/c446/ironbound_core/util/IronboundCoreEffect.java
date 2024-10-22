package com.c446.ironbound_core.util;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class IronboundCoreEffect extends MobEffect {
    public final List<ItemStack> curativeItems;

    static{

    }
    protected double tickDamage=0;
    protected int interval=0;
    protected DamageSource damageSource = null;

    public IronboundCoreEffect withDamage(double damagePerLevel, int tickInterval){
        var newEffect = new IronboundCoreEffect(this.getCategory(), this.getColor());
        newEffect.tickDamage = damagePerLevel;
        newEffect.interval = tickInterval;
        return newEffect;
    }

    public IronboundCoreEffect(MobEffectCategory effectCategory, int color) {
        this(effectCategory, color, List.of());
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (this.tickDamage != 0){

        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    public IronboundCoreEffect(MobEffectCategory type, int color, List<ItemStack> curativeItems) {
        super(type, color);
        this.curativeItems = curativeItems;
    }

}