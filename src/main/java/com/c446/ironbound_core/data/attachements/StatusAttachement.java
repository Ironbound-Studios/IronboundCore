package com.c446.ironbound_core.data.attachements;

import com.c446.ironbound_core.registries.IBAttachmentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.c446.ironbound_core.registries.IBAttachmentRegistry.STATUS_DATA;
import static com.c446.ironbound_core.registries.IBAttributeRegistry.*;
import static com.c446.ironbound_core.registries.IBMobEffectRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;

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


    public static void handleEffect(StatusTypes type, LivingEntity livingEntity) {
        System.out.println("handleEffect activated");

        switch (type) {
            case MADNESS -> {
                if (livingEntity.hasEffect(MADNESS)) {
                    int currentLevel = Objects.requireNonNull(livingEntity.getEffect(MADNESS)).getAmplifier();
                    int newLevel = (currentLevel > 5) ? 5 : currentLevel + 1;
                    int newDuration = 60 * 20 * newLevel;

                    livingEntity.removeEffect(MADNESS);
                    livingEntity.addEffect(new MobEffectInstance(MADNESS, newDuration, newLevel), livingEntity);
                } else {
                    livingEntity.addEffect(new MobEffectInstance(MADNESS, 20 * 60, 0));
                }
            }
            case FERVOR -> {
                if (livingEntity.hasEffect(FERVOR)) {
                    int currentLevel = Objects.requireNonNull(livingEntity.getEffect(FERVOR)).getAmplifier();
                    int newLevel = (currentLevel > 5) ? 5 : currentLevel + 1;
                    int newDuration = 60 * 20 * newLevel;

                    livingEntity.removeEffect(FERVOR);
                    livingEntity.addEffect(new MobEffectInstance(FERVOR, newDuration, newLevel), livingEntity);
                } else {
                    livingEntity.addEffect(new MobEffectInstance(FERVOR, 20 * 60, 0));
                }
            }
            case HOLLOW -> {
                if (livingEntity.hasEffect(HOLLOW)) {
                    int currentLevel = Objects.requireNonNull(livingEntity.getEffect(HOLLOW)).getAmplifier();
                    int newLevel = (currentLevel > 5) ? 5 : currentLevel + 1;
                    int newDuration = 60 * 20 * newLevel;

                    livingEntity.removeEffect(HOLLOW);
                    livingEntity.addEffect(new MobEffectInstance(HOLLOW, newDuration, newLevel), livingEntity);
                } else {
                    livingEntity.addEffect(new MobEffectInstance(HOLLOW, 20 * 60, 0));
                }
            }
            default -> {
            }
        }
        var data = livingEntity.getData(STATUS_DATA);
        data.setCurrentFromType(type, 0);
        livingEntity.setData(STATUS_DATA, data);
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

