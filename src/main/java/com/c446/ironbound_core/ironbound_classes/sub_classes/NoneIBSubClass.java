package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBSubClasses;
import net.minecraft.resources.ResourceLocation;

public class NoneIBSubClass extends IBSubClasses {
    public static NoneIBSubClass instance = new NoneIBSubClass(Ironbound.prefix("invalid_sub_class"));

    protected NoneIBSubClass(ResourceLocation classId) {
        super(classId);
    }


}
