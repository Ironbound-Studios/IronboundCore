package org.ironbound.ironbound_core.ironbound_classes;

import com.google.common.collect.HashMultimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.registries.IBSubClassRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class IBSubClass {
    public final ArrayList<IBClass> parents = new ArrayList<>();
    public final ResourceLocation subClassID;
    public final ResourceLocation school;
    public HashMap<Holder<Attribute>, AttributeModifier> attributes = new HashMap<>();

    public IBSubClass(ResourceLocation subClassID, ResourceLocation school, IBClass... parents) {
        this.subClassID = subClassID;
        this.school = school;
        this.parents.addAll(Arrays.asList(parents));
    }

    public IBSubClass(ResourceLocation subClassID, ResourceLocation school, IBClass parent) {
        this.subClassID = subClassID;
        this.school = school;
        this.parents.add(parent);
    }

    public List<Component> getClassPerks(int level) {
        ArrayList<Component> list = new ArrayList<>();
        if (level >= 3) {
            list.add(Component.translatable(IBSubClassRegistry.getSubFromLoc(getResource()).subClassID.getPath() + ".ability.1").withStyle(ChatFormatting.GRAY));
        }
        if (level >= 9) {
            list.add(Component.translatable(IBSubClassRegistry.getSubFromLoc(getResource()).subClassID.getPath() + ".ability.2").withStyle(ChatFormatting.GRAY));
        }
        if (level >= 15) {
            list.add(Component.translatable(IBSubClassRegistry.getSubFromLoc(getResource()).subClassID.getPath() + ".ability.3").withStyle(ChatFormatting.GRAY));
        }
        return list;
    }

    /**
     * @return returns the list of spells that the class will boost. For a blood sorcerer, stuff like Blood Needles, or other would be present.
     */
    public List<ResourceLocation> getInnateSpells() {
        return List.of();
    }

    /**
     * @return returns the list of spells that the class will boost. For a Healing Domain Clerc, stuff like Heal, Greater Heal, or Fortification would have been found.
     */
    public List<ResourceLocation> getBoostedSpells() {
        return List.of();
    }

    public boolean canUseFirstPerk(LivingEntity entity) {
        AtomicBoolean bool = new AtomicBoolean(false);
        ClassHelper.collectClassItems(entity).forEach(a -> {
            var data = ClassHelper.safeGetData(a);
            if (data.subClassID().equals(this.subClassID.getPath()) && data.level() >= 6) {
                bool.set(true);
            }
        });
        return ClassHelper.isSubClass(entity, this) && bool.get();
    }

    public boolean canUseSecondPerk(LivingEntity entity) {
        AtomicBoolean bool = new AtomicBoolean(false);
        ClassHelper.collectClassItems(entity).forEach(a -> {
            var data = ClassHelper.safeGetData(a);
            if (data.subClassID().equals(this.subClassID.getPath()) && data.level() >= 12) {
                bool.set(true);
            }
        });
        return ClassHelper.isSubClass(entity, this) && bool.get();
    }

    public boolean canUseThirdPerk(LivingEntity entity) {
        AtomicBoolean bool = new AtomicBoolean(false);
        ClassHelper.collectClassItems(entity).forEach(a -> {
            var data = ClassHelper.safeGetData(a);
            if (data.subClassID().equals(this.subClassID.getPath()) && data.level() >= 18) {
                bool.set(true);
            }
        });
        return ClassHelper.isSubClass(entity, this) && bool.get();
    }

    public void addAttribute(Holder<Attribute> attribute, AttributeModifier modifier) {
        this.attributes.putIfAbsent(attribute, modifier);
    }

    public void addAttributes(HashMap<Holder<Attribute>, AttributeModifier> attributes) {
        for (Holder<Attribute> key : attributes.keySet()) {
            this.attributes.putIfAbsent(key, attributes.get(key));
        }
    }

    public IBSubClass withAttribute(Holder<Attribute> attribute, AttributeModifier modifier) {
        this.attributes.putIfAbsent(attribute, modifier);
        return this;
    }

    public final HashMultimap<Holder<Attribute>, AttributeModifier> getAttributesForLevel(int level) {
        HashMultimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        for (var key : this.attributes.keySet()) {
            multimap.put(key, new AttributeModifier(this.attributes.get(key).id(), this.attributes.get(key).amount() * level, this.attributes.get(key).operation()));
        }

        return multimap;
    }

    public abstract ResourceLocation getResource();
}
