package com.c446.ironbound_core.data.attachements;

import com.c446.ironbound_core.Ironbound;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.c446.ironbound_core.registries.AttributeRegistry.*;
import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.*;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class ClassAttachement implements INBTSerializable<CompoundTag> {
    public ArrayList<ClassInstance> instances;

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        (this.instances).forEach(a -> tag.put(a.className.name(), a.serializeNBT(provider)));
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
        Arrays.stream(Classes.values()).forEach(
                a -> {
                    this.instances.add(
                            new ClassInstance(provider, tag.getCompound(a.name()))
                    );
                }
        );
    }


    public static class ClassInstance implements INBTSerializable<CompoundTag> {
        Classes className;
        SubClasses subClass;
        int level;

        ClassInstance(Classes className, SubClasses subClass, int level) {
            this.className = className;
            this.subClass = subClass;
            this.level = level;
        }

        ClassInstance(HolderLookup.Provider provider, CompoundTag tag) {
            this.deserializeNBT(provider, tag);
        }

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
            CompoundTag tag = new CompoundTag();

            tag.putString("class", this.className.name());
            tag.putString("sub_class", this.subClass.name());
            tag.putInt("level", this.subClass.ordinal());

            return tag;
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
            var aClass = tag.getString("class");
            var aSub = tag.getString("sub_class");
            var tagInt = tag.getInt("level");

            this.className = Classes.valueOf(aClass);
            this.subClass = SubClasses.valueOf(aSub);
            this.level = tagInt;
        }

        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes() {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (this.className.equals(Classes.PRIEST)) {

            } else if (this.className.equals(Classes.WARLOCK)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("warlock_health"), 5.0D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(FOCUS, new AttributeModifier(Ironbound.prefix("warlock_focus"), 0.075D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(COOLDOWN_REDUCTION, new AttributeModifier(Ironbound.prefix("warlock_cd"), 0.015D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(MAX_MANA, new AttributeModifier(Ironbound.prefix("warlock_mana"), 25.0D * this.level, AttributeModifier.Operation.ADD_VALUE));
            } else if (this.className.equals(Classes.WIZARD)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("rogue_health"), 5.0D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_CHANCE, new AttributeModifier(Ironbound.prefix("rogut_crit_rate"), 0.075D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix("hunter_crit_damage"), 0.15D * this.level, AttributeModifier.Operation.ADD_VALUE));
            } else if (this.className.equals(Classes.RANGER)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("rogue_health"), 5.0D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_CHANCE, new AttributeModifier(Ironbound.prefix("rogut_crit_rate"), 0.075D * this.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix("hunter_crit_damage"), 0.15D * this.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }
    }

    public static abstract class IronboundCoreClass {
        public Classes classID = Classes.NONE_CLASS;

        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            return new HashMap<>();
        }
    }

    public static class FighterClass extends IronboundCoreClass {
        public static FighterClass instance = new FighterClass();

        FighterClass() {
            this.classID = Classes.FIGHTER;
        }

        @Override
        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("fighter_health"), 6.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("fighter_vitality"), 0.5D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ATTACK_DAMAGE, new AttributeModifier(Ironbound.prefix("fighter_damage"), .75D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ATTACK_SPEED, new AttributeModifier(Ironbound.prefix("fighter_attack_speed"), 0.25D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }
    }

    public static class RogueClass extends IronboundCoreClass {
        public static RogueClass instance = new RogueClass();

        RogueClass() {
            this.classID = Classes.ROGUE;
        }

        @Override
        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("rogue_health"), 5.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("rogue_vitality"), 0.025D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_CHANCE, new AttributeModifier(Ironbound.prefix("rogue_crit_rate"), 0.075D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix("rogue_crit_damage"), 0.15D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }
    }

    public static class RangerClass extends IronboundCoreClass {
        public static RangerClass instance = new RangerClass();

        RangerClass() {
            this.classID = Classes.RANGER;
        }

        @Override
        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("ranger_health"), 5.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("ranger_vitality"), 0.025D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ARROW_DAMAGE, new AttributeModifier(Ironbound.prefix("ranger_arrow_damage"), 0.075D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ARROW_VELOCITY, new AttributeModifier(Ironbound.prefix("ranger_crit_damage"), 0.15D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }
    }

    public static class HunterClass extends IronboundCoreClass {
        public static HunterClass instance = new HunterClass();

        HunterClass() {
            this.classID = Classes.HUNTER;
        }

        @Override
        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("hunter_health"), 6.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("hunter_vitality"), 0.35D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ATTACK_DAMAGE, new AttributeModifier(Ironbound.prefix("hunter_damage"), 0.5D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(CRIT_DAMAGE, new AttributeModifier(Ironbound.prefix("hunter_crit_damage"), 0.1D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }
    }

    public static class Priest extends IronboundCoreClass {

        public static Priest instance = new Priest();
        Priest() {
            this.classID = Classes.RANGER;
        }

        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(INSIGHT, new AttributeModifier(Ironbound.prefix("priest_health"), 4.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(FOCUS, new AttributeModifier(Ironbound.prefix("priest_focus"), 0.075D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("priest_vitality"), 0.025D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(HOLY_SPELL_POWER, new AttributeModifier(Ironbound.prefix("priest_holy"), 0.075D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }

    }
    public static class RangerClass extends IronboundCoreClass {

        public static RangerClass instance = new RangerClass();
        RangerClass() {
            this.classID = Classes.RANGER;
        }

        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("ranger_health"), 5.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("ranger_vitality"), 0.025D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ARROW_DAMAGE, new AttributeModifier(Ironbound.prefix("ranger_arrow_damage"), 0.075D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ARROW_VELOCITY, new AttributeModifier(Ironbound.prefix("ranger_crit_damage"), 0.15D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }

    }
    public static class WizardClass extends IronboundCoreClass {
        public static WizardClass instance = new WizardClass();

        WizardClass() {
            this.classID = Classes.WIZARD;
        }

        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {

            }
            return map;
        }
    }

    public static class RangerClass extends IronboundCoreClass {

        public static RangerClass instance = new RangerClass();

        RangerClass() {
            this.classID = Classes.RANGER;
        }

        public HashMap<Holder<Attribute>, AttributeModifier> getAttributes(ClassInstance instance) {
            var map = new HashMap<Holder<Attribute>, AttributeModifier>();
            if (instance.className.equals(this.classID)) {
                map.put(MAX_HEALTH, new AttributeModifier(Ironbound.prefix("ranger_health"), 5.0D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(VITALITY, new AttributeModifier(Ironbound.prefix("ranger_vitality"), 0.025D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ARROW_DAMAGE, new AttributeModifier(Ironbound.prefix("ranger_arrow_damage"), 0.075D * instance.level, AttributeModifier.Operation.ADD_VALUE));
                map.put(ARROW_VELOCITY, new AttributeModifier(Ironbound.prefix("ranger_crit_damage"), 0.15D * instance.level, AttributeModifier.Operation.ADD_VALUE));
            }
            return map;
        }
    }


    public static enum Classes {
        FIGHTER(),
        RANGER(),
        ROGUE(),
        HUNTER(),
        WIZARD(),
        WARLOCK(),
        SORCERER(),
        PRIEST(),
        NONE_CLASS;
    }

    public static enum SubClasses {
        HOLY(new Classes[]{Classes.FIGHTER, Classes.PRIEST}),
        ENDER(new Classes[]{Classes.ROGUE, Classes.WARLOCK}),
        EVOKER(new Classes[]{Classes.ROGUE, Classes.WIZARD}),
        FIRE(new Classes[]{Classes.FIGHTER, Classes.SORCERER}),
        LIGHTNING(new Classes[]{Classes.WIZARD, Classes.RANGER}),
        NATURE(new Classes[]{Classes.PRIEST, Classes.RANGER, Classes.HUNTER}),
        BLOOD(new Classes[]{Classes.WARLOCK, Classes.HUNTER}),
        ICE(new Classes[]{Classes.ROGUE, Classes.SORCERER}),
        ELDRITCH(new Classes[]{Classes.WARLOCK, Classes.PRIEST, Classes.FIGHTER});

        public final Classes[] canObtain;

        SubClasses(Classes[] overClass) {
            this.canObtain = overClass;
        }
    }


}
