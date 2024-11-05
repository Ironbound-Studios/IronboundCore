package org.ironbound.ironbound_core.util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.ironbound.ironbound_core.registries.IBDamageSourcesReg;
import org.ironbound.ironbound_core.registries.IBMobEffectRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DecayEffect extends IronboundCoreEffect {
    public DecayEffect(MobEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    public DecayEffect(MobEffectCategory type, int color, List<ItemStack> curativeItems) {
        super(type, color, curativeItems);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        System.out.println("decay triggered");
        if (livingEntity.tickCount % 20 == 0){
            livingEntity.hurt(new DamageSource(IBDamageSourcesReg.getFromKey(livingEntity, IBDamageSourcesReg.DECAY)), amplifier);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
