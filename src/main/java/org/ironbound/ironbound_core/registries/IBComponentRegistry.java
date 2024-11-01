package org.ironbound.ironbound_core.registries;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.ironbound.ironbound_core.ironbound_classes.ClassInstance;

import java.util.function.UnaryOperator;

import static org.ironbound.ironbound_core.data.components.ClassDataCodec.BASIC_CLASS_CODEC;
import static org.ironbound.ironbound_core.data.components.ClassDataCodec.CLASS_CODEC;

public class IBComponentRegistry {
    private static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(IronsSpellbooks.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ClassInstance>> CLASS_COMPONENT = register("class_instance_component", (builder) -> builder.persistent(BASIC_CLASS_CODEC).networkSynchronized(CLASS_CODEC).cacheEncoding());

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String pName, UnaryOperator<DataComponentType.Builder<T>> pBuilder) {
        return COMPONENTS.register(pName, () -> pBuilder.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        COMPONENTS.register(eventBus);
    }
    //(builder) -> builder.persistent(AffinityData.CODEC).networkSynchronized(AffinityData.STREAM_CODEC).cacheEncoding()
}
