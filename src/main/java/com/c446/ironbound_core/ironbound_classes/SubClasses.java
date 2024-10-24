package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.data.attachements.ClassAttachement;
import com.c446.ironbound_core.data.components.classescomponents.Classes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class SubClasses {
    public final ArrayList<IBClass> parents = new ArrayList<>();
    public HashMap<Holder<Attribute>, AttributeModifier> attributes;
    public final ResourceLocation subClassID;

    public SubClasses(ResourceLocation subClassID, IBClass... parents){
        this.subClassID = subClassID;
        this.parents.addAll(Arrays.asList(parents));
    }

    public SubClasses(IBClass parent, ResourceLocation subClassID){
        this.subClassID = subClassID;
        this.parents.add(parent);
    }

    public void addAttribute(Holder<Attribute> attribute, AttributeModifier modifier){
        this.attributes.putIfAbsent(attribute, modifier);
    }

    public void addAttributes(HashMap<Holder<Attribute>, AttributeModifier> attributes){
        for (Holder<Attribute> key : attributes.keySet()){
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
