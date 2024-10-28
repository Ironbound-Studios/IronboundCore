package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.resources.ResourceLocation;

public class NoneSubClass extends IBSubClass {
    public static NoneSubClass instance = new NoneSubClass(Ironbound.prefix("invalid_sub_class"), SchoolRegistry.EVOCATION_RESOURCE);

    protected NoneSubClass(ResourceLocation classId, ResourceLocation type) {
        super(classId, type);
    }


    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }
}
