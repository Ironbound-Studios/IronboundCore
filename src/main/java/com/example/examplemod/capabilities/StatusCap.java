package com.example.examplemod.capabilities;

import com.example.examplemod.events.MobStatusTriggeredEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.NoteBlockEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;
import java.util.logging.Level;

import static com.example.examplemod.registries.AttributeRegistry.FOCUS;
import static com.example.examplemod.registries.AttributeRegistry.VITALITY;

public class StatusCap<K extends LivingEntity> {

    StatusCap(K entity) {
        createResistances(entity);
    }

    int madnessMax = 0;
    int madnessCurrent = 0;
    int soulShatteredMax = 0;
    int soulShatteredCurrent = 0;
    int overChargedMax = 0;
    int overChargedCurrent = 0;
    int frostMax = 0;
    int frostCurrent = 0;
    int bleedMax = 0;
    int bleedCurrent = 0;
    int hollowMax = 0;
    int hollowCurrent = 0;
    int fervorMax = 0;
    int fervorCurrent = 0;

    public void createResistances(K entity) {
        madnessMax = ((int) (20 * entity.getAttributeValue(FOCUS)));
        hollowMax = ((int) (15 * entity.getAttributeValue(FOCUS)));
        fervorMax = ((int) (10 * entity.getAttributeValue(FOCUS)));
        soulShatteredMax = ((int) (10 * entity.getAttributeValue(FOCUS)));
        bleedMax = ((int) (5 * (entity.getAttributeValue(VITALITY) + entity.getHealth() / 2)));
        frostMax = ((int) (3 * (entity.getAttributeValue(VITALITY) + entity.getAttributeValue(FOCUS))));
        overChargedMax = ((int) (3 * (entity.getAttributeValue(VITALITY) + entity.getAttributeValue(FOCUS))));
    }

    public ArrayList<StatusTypes> checkStatus(K player) {
        ArrayList<StatusTypes> list = new ArrayList<StatusTypes>();
        if (hollowCurrent > hollowMax) {
            list.add(StatusTypes.HOLLOW);
        }
        if (fervorCurrent > fervorMax) {
            list.add(StatusTypes.FERVOR);
        }
        if (madnessCurrent > madnessMax) {
            list.add(StatusTypes.MADNESS);
        }
        if (bleedCurrent > bleedMax) {
            list.add(StatusTypes.BLEED);
        }
        if (frostCurrent > frostMax) {
            list.add(StatusTypes.FROST);
        }
        if (soulShatteredCurrent > soulShatteredMax) {
            list.add(StatusTypes.WEAK_MIND);
        }
        if (overChargedCurrent > overChargedMax) {
            list.add(StatusTypes.OVERCHARGED);
        }
        NeoForge.EVENT_BUS.post(new MobStatusTriggeredEvent.Pre(player, list));
        return list;
    }

    public int getMadnessMax() {
        return madnessMax;
    }

    public void setMadnessMax(int madnessMax) {
        this.madnessMax = madnessMax;
    }

    public int getMadnessCurrent() {
        return madnessCurrent;
    }

    public void setMadnessCurrent(int madnessCurrent) {
        this.madnessCurrent = madnessCurrent;
    }

    public int getFrostMax() {
        return frostMax;
    }

    public void setFrostMax(int frostMax) {
        this.frostMax = frostMax;
    }

    public int getFrostCurrent() {
        return frostCurrent;
    }

    public void setFrostCurrent(int frostCurrent, Player entity) {
        this.frostCurrent = frostCurrent;
    }

    public int getBleedMax() {
        return bleedMax;
    }

    public void setBleedMax(int bleedMax) {
        this.bleedMax = bleedMax;
    }

    public int getBleedCurrent() {
        return bleedCurrent;
    }

    public void setBleedCurrent(int bleedCurrent) {
        this.bleedCurrent = bleedCurrent;
    }

    public int getSoulShatteredCurrent() {
        return soulShatteredCurrent;
    }

    public void setSoulShatteredCurrent(int soulShatteredCurrent) {
        this.soulShatteredCurrent = soulShatteredCurrent;
    }

    public int getSoulShatteredMax() {
        return soulShatteredMax;
    }

    public void setSoulShatteredMax(int soulShatteredMax) {
        this.soulShatteredMax = soulShatteredMax;
    }

    public int getHollowMax() {
        return hollowMax;
    }

    public void setHollowMax(int hollowMax) {
        this.hollowMax = hollowMax;
    }

    public int getHollowCurrent() {
        return hollowCurrent;
    }

    public void setHollowCurrent(int hollowCurrent, Player entity) {
        this.hollowCurrent = hollowCurrent;
    }

    public int getOverChargedMax() {
        return overChargedMax;
    }

    public void setOverChargedMax(int overChargedMax) {
        this.overChargedMax = overChargedMax;
    }

    public int getOverChargedCurrent() {
        return overChargedCurrent;
    }

    public void setOverChargedCurrent(int overChargedCurrent, Player entity) {
        this.overChargedCurrent = overChargedCurrent;
    }

    public int getFervorMax() {
        return fervorMax;
    }

    public void setFervorMax(int fervorMax) {
        this.fervorMax = fervorMax;
    }

    public int getFervorCurrent() {
        return fervorCurrent;
    }

    public void setFervorCurrent(int fervorCurrent, Player entity) {
        System.out.println("fervor " + this.fervorCurrent + " -> " + fervorCurrent);
        this.fervorCurrent = fervorCurrent;
    }


}
