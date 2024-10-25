package com.c446.ironbound_core.data.attachements;

import com.c446.ironbound_core.data.components.classescomponents.Classes;
import com.c446.ironbound_core.ironbound_classes.ClassInstance;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClassAttachement implements INBTSerializable<CompoundTag> {
    public ArrayList<ClassInstance> instances = new ArrayList<>();

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        (this.instances).forEach(a -> tag.put(a.classID.toString(), a.serializeNBT(provider)));
        return tag;
    }

    public boolean containsClass(IBClass ibClass) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        instances.forEach(classInstance -> {
            if (classInstance.classID.equals(ibClass.classId)) {
                atomicBoolean.set(true);
            }
        });
        return atomicBoolean.get();
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
        Arrays.stream(Classes.values()).forEach(
                a -> {
                    this.instances.add(
                            new ClassInstance(provider, tag.getCompound(a.name()))
                    );
                }
        );
    }
}
