package com.c446.ironbound_core.data.attachements;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import static com.c446.ironbound_core.registries.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;

public class StatusAttachement implements INBTSerializable<CompoundTag> {
    private int bleedCurrent;
    private int fervorCurrent;
    private int frostCurrent;
    private int hollowCurrent;
    private int madnessCurrent;
    private int overChargedCurrent;
    private int soulShatteredCurrent;

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

    public void setBleedCurrent(int bleedCurrent) {
        this.bleedCurrent = bleedCurrent;
    }

    public void setFervorCurrent(int fervorCurrent) {
        this.fervorCurrent = fervorCurrent;
    }

    public void setFrostCurrent(int frostCurrent) {
        this.frostCurrent = frostCurrent;
    }

    public void setHollowCurrent(int hollowCurrent) {
        this.hollowCurrent = hollowCurrent;
    }

    public void setMadnessCurrent(int madnessCurrent) {
        this.madnessCurrent = madnessCurrent;
    }

    public void setOverChargedCurrent(int overChargedCurrent) {
        this.overChargedCurrent = overChargedCurrent;
    }

    public void setSoulShatteredCurrent(int soulShatteredCurrent) {
        this.soulShatteredCurrent = soulShatteredCurrent;
    }


    public StatusAttachement() {
        this.bleedCurrent = 0;
        this.fervorCurrent = 0;
        this.frostCurrent = 0;
        this.hollowCurrent = 0;
        this.madnessCurrent = 0;
        this.overChargedCurrent = 0;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("bleed", this.bleedCurrent);
        tag.putInt("fervor", this.fervorCurrent);
        tag.putInt("frost", this.frostCurrent);
        tag.putInt("hollow", this.hollowCurrent);
        tag.putInt("madness", this.madnessCurrent);
        tag.putInt("overload", this.overChargedCurrent);
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag tag) {
        this.bleedCurrent = tag.getInt("bleed");
        this.fervorCurrent = tag.getInt("fervor");
        this.frostCurrent = tag.getInt("frost");
        this.hollowCurrent = tag.getInt("hollow");
        this.madnessCurrent = tag.getInt("madness");
        this.overChargedCurrent = tag.getInt("overload");
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

    protected double getMaxFromType(LivingEntity entity, StatusTypes types) {
        return switch (types) {
            case HOLLOW, FERVOR, WEAK_MIND ->
                    10 * entity.getAttributeValue(FOCUS) + 20 * entity.getAttributeValue(INSIGHT);
            case BLEED, FROST, OVERCHARGED ->
                    10 * entity.getAttributeValue(MAX_HEALTH) + 20 * entity.getAttributeValue(VITALITY);
            case MADNESS -> 15 * entity.getAttributeValue(FOCUS) - 5 * entity.getAttributeValue(INSIGHT);
            default -> -1;
        };
    }
}

