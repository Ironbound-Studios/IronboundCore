package com.c446.ironbound_core.ironbound_classes;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class ClassInstance implements INBTSerializable<CompoundTag> {
    public ResourceLocation classNameID;
    public SubClasses subClassID;
    int level;

    public ClassInstance(ResourceLocation classNameID, SubClasses subClassID, int level) {
        this.classNameID = classNameID;
        this.subClassID = subClassID;
        this.level = level;
    }

    public ClassInstance(HolderLookup.Provider provider, CompoundTag tag) {
        this.deserializeNBT(provider, tag);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putString("class", this.classNameID.toString());
        tag.putString("sub_class", this.subClassID.subClassID.toString());
        tag.putInt("level", this.level);

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        var aClass = tag.getString("class");
        var aSub = tag.getString("sub_class");
        var tagInt = tag.getInt("level");

        this.classNameID = ResourceLocation.parse(aClass);
        this.subClassID = null; //SubClassRegistry.get(aSub);
        this.level = tagInt;
    }

}
