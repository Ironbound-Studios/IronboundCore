package org.ironbound.ironbound_core.util;


import io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingBoss;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import org.ironbound.ironbound_core.registries.IBDamageSourcesReg;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * For your inconvenience, i am not writing any useful doc.
 * JK~ this should be simple, simplily an inheritor of MobEffect that has got some methods to use less inheritance for the future(TM)
 */
public class    IronboundCoreEffect extends MobEffect {
    public final List<ItemStack> curativeItems;

    protected double tickDamage = 0;
    protected int interval = 0;
    protected ResourceKey<DamageType> type = null;

    public IronboundCoreEffect(MobEffectCategory effectCategory, int color) {
        this(effectCategory, color, List.of());
    }

    public IronboundCoreEffect(MobEffectCategory type, int color, List<ItemStack> curativeItems) {
        super(type, color);
        this.curativeItems = curativeItems;
    }

    /**
     * @param damagePerLevel : the amount of damage dealt per tick interval.
     * @param tickInterval   : the cooldown between each interval. minimum is one, ZERO is forbidden !
     * @param type           : the damage type that will be used.
     * @return : returns a new @link{com.c446.ironbound_core.util.IronboundCoreEffect} with the applied change.
     */
    public IronboundCoreEffect withDamage(double damagePerLevel, int tickInterval, ResourceKey<DamageType> type) {
        var newEffect = new IronboundCoreEffect(this.getCategory(), this.getColor());
        newEffect.tickDamage = damagePerLevel;
        newEffect.interval = tickInterval;
        newEffect.type = type;
        return newEffect;
    }

    @Override
    public @NotNull IronboundCoreEffect addAttributeModifier(@NotNull Holder<Attribute> pAttribute, @NotNull ResourceLocation pId, double pAmount, AttributeModifier.@NotNull Operation pOperation) {
        return (IronboundCoreEffect) super.addAttributeModifier(pAttribute, pId, pAmount, pOperation);
    }

    //    public IronboundCoreEffect addAttributeModifier(@NotNull Holder<Attribute> pAttribute, @NotNull ResourceLocation pId, double pAmount, AttributeModifier.@NotNull Operation pOperation) {
//        this.attributeModifiers.put(pAttribute, new MobEffect.AttributeTemplate(pId, pAmount, pOperation));
//        MobEffect.AttributeTemplate
//
//        return this;
//    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (livingEntity.tickCount % this.interval == 0) {
            System.out.println("tick applied");
            livingEntity.invulnerableTime = 0;

            livingEntity.hurt(new DamageSource(IBDamageSourcesReg.getFromKey(livingEntity, type)), (float) this.tickDamage * amplifier);
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return this.tickDamage != 0;
    }
}