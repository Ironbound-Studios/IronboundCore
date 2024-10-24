package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.ironbound_classes.main_classes.FighterClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.EldritchKnight;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public class ZClassRegistry {
    public static HashMap<ResourceLocation, IBClass> classes = new HashMap<>();
    public static HashMap<ResourceLocation, SubClasses> subClasses = new HashMap<>();

    static{
        classes.put(FighterClass.instance.classId, FighterClass.instance);

        subClasses.put(EldritchKnight.instance.subClassID, EldritchKnight.instance);
    }

    public static IBClass getMainFromLoc(ResourceLocation loc){
        for (var key : classes.keySet()){
            if (classes.get(key).classId.equals(loc)){
                return (classes.get(key));
            }
        }
        return null;
    }

    public static SubClasses getSubFromLoc(ResourceLocation loc){
        for (var key : subClasses.keySet()){
            if (subClasses.get(key).subClassID.equals(loc)){
                return (subClasses.get(key));
            }
        }
        return null;
    }
}
