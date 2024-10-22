package com.example.examplemod.registries;

import com.example.examplemod.components.classescomponents.ClassesRecord;
import io.netty.buffer.ByteBuf;
import io.redspace.ironsspellbooks.IronsSpellbooks;
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

    public static final StreamCodec<ByteBuf, ClassesRecord> CLASSES_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ClassesRecord::level,
            ByteBufCodecs.STRING_UTF8, ClassesRecord::main_class,
            ByteBufCodecs.STRING_UTF8, ClassesRecord::sub_class,
            ClassesRecord::new
    );
}
