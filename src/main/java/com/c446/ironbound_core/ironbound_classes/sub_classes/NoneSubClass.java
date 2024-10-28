package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import net.minecraft.resources.ResourceLocation;

public class NoneSubClass extends IBSubClass {
    public static NoneSubClass instance = new NoneSubClass(Ironbound.prefix("invalid_sub_class"));

    protected NoneSubClass(ResourceLocation classId) {
        super(classId);
    }


    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }
}
