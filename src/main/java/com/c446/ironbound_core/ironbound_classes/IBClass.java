package com.c446.ironbound_core.ironbound_classes;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.HashMap;

public abstract class IBClass {
    public HashMap<Holder<Attribute>, AttributeModifier> attributes = new HashMap<>();
    public final ResourceLocation classId;

    public static int getMastery(int level){
        return (int) (Math.floor(level/5.0D) + 2);
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

    public final HashMap<Holder<Attribute>, AttributeModifier> getAttributesForLevel(int level) {
        var map = new HashMap<Holder<Attribute>, AttributeModifier>();
        for (var key : this.attributes.keySet()) {
            map.put(key, new AttributeModifier(this.attributes.get(key).id(), this.attributes.get(key).amount() * level, this.attributes.get(key).operation()));
        }
        return map;
    }
}
