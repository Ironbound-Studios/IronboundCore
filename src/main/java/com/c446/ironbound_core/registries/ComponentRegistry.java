package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.ClassInstance;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ComponentRegistry {
    private static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(IronsSpellbooks.MODID);

    public static void register(IEventBus eventBus) {
        COMPONENTS.register(eventBus);
    }
    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String pName, UnaryOperator<DataComponentType.Builder<T>> pBuilder) {
        return COMPONENTS.register(pName, () -> pBuilder.apply(DataComponentType.builder()).build());
    }

    public static final Codec<ClassInstance> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("main_class").forGetter(ClassInstance::classID),
                    Codec.STRING.fieldOf("sub_class").forGetter(ClassInstance::subClassID),
                    Codec.INT.fieldOf("level").forGetter(ClassInstance::level)
            ).apply(instance, ClassInstance::new)
    );

    public static final StreamCodec<ByteBuf, ClassInstance> CLASS_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ClassInstance::classID,
            ByteBufCodecs.STRING_UTF8, ClassInstance::subClassID,
            ByteBufCodecs.INT, ClassInstance::level,
            ClassInstance::new
    );


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ClassInstance>> CLASS_COMPONENT = register("class_instance_component", (builder) -> builder.persistent(BASIC_CODEC).networkSynchronized(CLASS_CODEC).cacheEncoding());
    //(builder) -> builder.persistent(AffinityData.CODEC).networkSynchronized(AffinityData.STREAM_CODEC).cacheEncoding()
}
