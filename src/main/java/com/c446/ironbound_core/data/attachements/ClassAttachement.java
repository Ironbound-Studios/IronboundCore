package com.c446.ironbound_core.data.attachements;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.data.components.classescomponents.Classes;
import com.c446.ironbound_core.ironbound_classes.ClassInstance;
import com.c446.ironbound_core.ironbound_classes.SubClasses;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.c446.ironbound_core.registries.AttributeRegistry.*;
import static dev.shadowsoffire.apothic_attributes.api.ALObjects.Attributes.*;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class ClassAttachement implements INBTSerializable<CompoundTag> {
    public ArrayList<ClassInstance> instances;

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        (this.instances).forEach(a -> tag.put(a.classNameID.toString(), a.serializeNBT(provider)));
        return tag;
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
