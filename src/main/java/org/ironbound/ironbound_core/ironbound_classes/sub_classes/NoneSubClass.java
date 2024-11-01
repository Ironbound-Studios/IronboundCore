package org.ironbound.ironbound_core.ironbound_classes.sub_classes;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.resources.ResourceLocation;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBSubClass;

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
