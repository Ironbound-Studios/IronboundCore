package com.c446.ironbound_core.ironbound_classes.main_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import net.minecraft.resources.ResourceLocation;

public class NoneClass extends IBClass {
    public static NoneClass instance = new NoneClass(Ironbound.prefix("invalid_class"));

    protected NoneClass(ResourceLocation classId) {
        super(classId);
    }
}
