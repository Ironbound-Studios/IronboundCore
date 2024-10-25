package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.registries.AttachmentReg;
import com.google.common.collect.HashMultimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.*;

import static com.c446.ironbound_core.registries.ClassRegistry.getMainFromLoc;
import static com.c446.ironbound_core.registries.SubClassRegistry.getSubFromLoc;

public class ClassHelper {
    public static ArrayList<ClassInstance> getInstanceFor(LivingEntity entity) {
        var list = new ArrayList<ClassInstance>();
        if (entity.hasData(AttachmentReg.LEVEL_DATA)) {
            list.addAll(entity.getData(AttachmentReg.LEVEL_DATA).instances);
        }
        return list;
    }

    //TODO: OPTIMIZE CODE.

//        public static ArrayList<HashMap<Holder<Attribute>, AttributeModifier>> gatherAttributes(LivingEntity player) {
//            ArrayList<HashMap<Holder<Attribute>, AttributeModifier>> attributes = new ArrayList<>();
//            var instances = getInstanceFor(player);
//            for (var i : instances) {
//                attributes.add(ZClassRegistry.getMainFromLoc(i.classID).getAttributesForLevel(i.getLevel()));
//                attributes.add(ZClassRegistry.getSubFromLoc(i.subClassID).getAttributesForLevel(i.getLevel()));
//            }
//            return attributes;
//        }
//
//        public static HashMultimap<Holder<Attribute>, AttributeModifier> getCleanAttributes(LivingEntity entity) {
//            HashMultimap<Holder<Attribute>, AttributeModifier> returningMap = HashMultimap.create();
//            var attributes = gatherAttributes(entity);
//            for (var maps : attributes) {
//                for (var attributeHolder : maps.keySet())
//                    if (returningMap.containsKey(attributeHolder)) {
//                        Set<AttributeModifier> list = returningMap.get(attributeHolder);
//                        list.add(maps.get(attributeHolder));
//                        returningMap.removeAll(attributeHolder);
//                        returningMap.putAll(attributeHolder, list);
//                    } else {
//                        var list = new ArrayList<AttributeModifier>();
//                        list.add(maps.get(attributeHolder));
//                        returningMap.putAll(attributeHolder, list);
//                    }
//            }
//            return returningMap;
//        }

    public static List<Map<Holder<Attribute>, AttributeModifier>> gatherAttributes(LivingEntity player) {
        List<Map<Holder<Attribute>, AttributeModifier>> attributes = new ArrayList<>();
        for (var instance : getInstanceFor(player)) {
            attributes.add(getMainFromLoc(instance.classID).getAttributesForLevel(instance.getLevel()));
            attributes.add(getSubFromLoc(instance.subClassID).getAttributesForLevel(instance.getLevel()));
        }
        return attributes;
    }

    public static HashMultimap<Holder<Attribute>, AttributeModifier> getCleanAttributes(LivingEntity entity) {
        HashMultimap<Holder<Attribute>, AttributeModifier> combinedAttributes = HashMultimap.create();
        for (var attributeMap : gatherAttributes(entity)) {
            for (var entry : attributeMap.entrySet()) {
                combinedAttributes.put(entry.getKey(), entry.getValue());
            }
        }
        return combinedAttributes;
    }

    public static void setPlayerAttribute(Player player) {
        player.getAttributes().addTransientAttributeModifiers(getCleanAttributes(player));
    }
}
