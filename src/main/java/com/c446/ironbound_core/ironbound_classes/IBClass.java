package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.registries.IBClassRegistry;
import com.google.common.collect.HashMultimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class IBClass {
    public static IBClass instance;

    public List<Component> getClassPerks(int level, ItemStack stack) {
        return new ArrayList<>();
    }

    public ResourceLocation getClassId() {
        return classId;
    }

    public List<Component> getSubClassPerks(int level) {
        ArrayList<Component> list = new ArrayList<>();
        if (level >= 6) {
            list.add(Component.translatable(IBClassRegistry.getMainFromLoc(getResource()).classId.getPath() + ".ability.1").withStyle(ChatFormatting.GRAY));
        }
        if (level >= 12) {
            list.add(Component.translatable(IBClassRegistry.getMainFromLoc(getResource()).classId.getPath() + ".ability.2").withStyle(ChatFormatting.GRAY));
        }
        if (level >= 18) {
            list.add(Component.translatable(IBClassRegistry.getMainFromLoc(getResource()).classId.getPath() + ".ability.3").withStyle(ChatFormatting.GRAY));
        }
        return list;
    }


    public HashMap<Holder<Attribute>, AttributeModifier> attributes = new HashMap<>();

    public final ResourceLocation classId;

    public static int getMastery(LivingEntity player) {
        return (int) (Math.floor(ClassHelper.safeGetData(ClassHelper.collectClassItems(player).getFirst()).level() / 5.0D) + 2);
    }

    protected IBClass(ResourceLocation classId) {
        this.classId = classId;
    }

    public void addAttribute(Holder<Attribute> attribute, AttributeModifier modifier) {
        this.attributes.putIfAbsent(attribute, modifier);
    }


    public void addAttributes(HashMap<Holder<Attribute>, AttributeModifier> attributes) {
        for (Holder<Attribute> key : attributes.keySet()) {
            this.attributes.putIfAbsent(key, attributes.get(key));
        }
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
