package org.ironbound.ironbound_core.ironbound_classes.main_classes;

import net.minecraft.resources.ResourceLocation;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;

public class NoneClass extends IBClass {
    public static NoneClass instance = new NoneClass(Ironbound.prefix("invalid_class"));

    protected NoneClass(ResourceLocation classId) {
        super(classId);
    }

    @Override
    public ResourceLocation getResource() {
        return this.classId;
    }
}
