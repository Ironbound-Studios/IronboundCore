package org.ironbound.ironbound_core.data.attachements;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;
import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.GENERIC_DATA;
import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.STATUS_DATA;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.*;
import static org.ironbound.ironbound_core.registries.IBMobEffectRegistry.*;

public class StatusAttachement implements INBTSerializable<CompoundTag> {
    private int bleedCurrent;
    private int fervorCurrent;
    private int frostCurrent;
    private int hollowCurrent;
    private int madnessCurrent;
    private int overChargedCurrent;
    private int soulShatteredCurrent;

    public StatusAttachement() {
        this.bleedCurrent = 0;
        this.fervorCurrent = 0;
        this.frostCurrent = 0;
        this.hollowCurrent = 0;
        this.madnessCurrent = 0;
        this.overChargedCurrent = 0;
    }


    public static void increaseEffectAmpAndLength(Holder<MobEffect> effect, LivingEntity livingEntity) {
        if (livingEntity.hasEffect(effect)) {
            int currentLevel = Objects.requireNonNull(livingEntity.getEffect(effect)).getAmplifier();
            int newLevel = (currentLevel > 5) ? 5 : currentLevel + 1;
            int newDuration = 60 * 20 * newLevel;

            livingEntity.removeEffect(effect);
            livingEntity.addEffect(new MobEffectInstance(effect, newDuration, newLevel), livingEntity);
        } else {
            livingEntity.addEffect(new MobEffectInstance(effect, 20 * 60, 0));
        }
    }

    public static void handleEffect(StatusTypes type, LivingEntity livingEntity) {
        switch (type) {
            case MADNESS -> {
                increaseEffectAmpAndLength(MADNESS, livingEntity);
            }
            case FERVOR -> {
                increaseEffectAmpAndLength(FERVOR, livingEntity);
            }
            case HOLLOW -> {
                increaseEffectAmpAndLength(HOLLOW, livingEntity);
            }
            case FROST -> {
                increaseEffectAmpAndLength(FROSTED_EFFECT, livingEntity);
            }
            case BLEED -> {
                increaseEffectAmpAndLength(ALObjects.MobEffects.BLEEDING, livingEntity);
            }
            default -> {
            }
        }
        var data = livingEntity.getData(STATUS_DATA);
        data.setCurrentFromType(type, 0);
        livingEntity.setData(STATUS_DATA, data);
    }

    public static void tick(LivingEntity entity) {
        if (entity.hasData(STATUS_DATA)) {
            var data = entity.getData(STATUS_DATA);
            data.hollowCurrent -= (int) (data.hollowCurrent / 100D);
            data.madnessCurrent -= (int) (data.madnessCurrent / 100D);
            data.fervorCurrent -= (int) (data.fervorCurrent / 100D);
            data.bleedCurrent -= (int) (data.bleedCurrent / 100D);
            data.frostCurrent -= (int) (data.frostCurrent / 100D);
            entity.setData(STATUS_DATA, data);
        }
        if (entity.hasData(GENERIC_DATA)) {
            var data = entity.getData(GENERIC_DATA);

        }
    }

    public void addTo(StatusTypes type, int amount) {
        switch (type) {
            case MADNESS -> this.madnessCurrent += amount;
            case FERVOR -> this.fervorCurrent += amount;
            case HOLLOW -> this.hollowCurrent += amount;
            case StatusTypes.BLEED -> this.bleedCurrent += amount;
            case StatusTypes.FROST -> this.frostCurrent += amount;
            case StatusTypes.OVERCHARGED -> this.overChargedCurrent += amount;
        }
    }

    public int getBleedCurrent() {
        return bleedCurrent;
    }

    public int getFervorCurrent() {
        return fervorCurrent;
    }

    public int getFrostCurrent() {
        return frostCurrent;
    }

    public int getHollowCurrent() {
        return hollowCurrent;
    }

    public int getMadnessCurrent() {
        return madnessCurrent;
    }

    public int getOverChargedCurrent() {
        return overChargedCurrent;
    }

    public int getSoulShatteredCurrent() {
        return soulShatteredCurrent;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag tag) {
    }

    public int getCurrentFromType(StatusTypes types) {
        return switch (types) {
            case HOLLOW -> this.hollowCurrent;
            case FERVOR -> this.fervorCurrent;
            case MADNESS -> this.madnessCurrent;
            case BLEED -> this.bleedCurrent;
            case FROST -> this.frostCurrent;
            case WEAK_MIND -> this.soulShatteredCurrent;
            case OVERCHARGED -> this.overChargedCurrent;
            default -> -1;
        };
    }

    public void setCurrentFromType(StatusTypes types, int amount) {
        switch (types) {
            case HOLLOW -> this.hollowCurrent = amount;
            case FERVOR -> this.fervorCurrent = amount;
            case MADNESS -> this.madnessCurrent = amount;
            case BLEED -> this.bleedCurrent = amount;
            case FROST -> this.frostCurrent = amount;
            case WEAK_MIND -> this.soulShatteredCurrent = amount;
            case OVERCHARGED -> this.overChargedCurrent = amount;
            default -> {
            }
        }
    }


    public double getMaxFromType(LivingEntity entity, StatusTypes types) {
        var x = switch (types) {
            case HOLLOW, FERVOR, WEAK_MIND ->
                    300 + (entity.getAttributeValue(FOCUS) + entity.getAttributeValue(INSIGHT));
            case BLEED, FROST, OVERCHARGED ->
                    300 + (entity.getAttributeValue(MAX_HEALTH) + 20 * entity.getAttributeValue(VITALITY));
            case MADNESS -> 200 + 15 * entity.getAttributeValue(FOCUS) - 5 * entity.getAttributeValue(INSIGHT);
            default -> -1;
        };
        System.out.println("getMaxFromType : " + types.name().toLowerCase() + " " + x);
        return x;
    }
}

