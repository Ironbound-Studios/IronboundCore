package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.registries.AttachmentReg;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassHelper {
    public static ArrayList<ClassInstance> getInstanceFor(LivingEntity entity){
        var list = new ArrayList<ClassInstance>();
        if (entity.hasData(AttachmentReg.LEVEL_DATA)){
            list.addAll(entity.getData(AttachmentReg.LEVEL_DATA).instances);
        }
        return list;
    }

    public static ArrayList<HashMap<Holder<Attribute>, AttributeModifier>> gatherAttributes(LivingEntity player){
        ArrayList<HashMap<Holder<Attribute>, AttributeModifier>> attributes = new ArrayList<>();
        var instances = getInstanceFor(player);
        for (var i : instances){
            attributes.add(ZClassRegistry.getMainFromLoc(i.classNameID).getAttributesForLevel(i.getLevel()));
            attributes.add(ZClassRegistry.getSubFromLoc(i.subClassID).getAttributesForLevel(i.getLevel()));
        }
    return attributes;
    }
}
