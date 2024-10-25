package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.Ironbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;


@EventBusSubscriber(modid = Ironbound.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AttributeRegistry {
    public static final HashMap<DeferredHolder<Attribute, Attribute>, UUID> UUIDS = new HashMap<DeferredHolder<Attribute, Attribute>, UUID>();
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Ironbound.MODID);

    public static final DeferredHolder<Attribute, Attribute> INSIGHT = registerAttribute("ironbounds_artefacts.attribute.insight", (id) -> new RangedAttribute(id, 1.0D, 1.0D, 20D).setSyncable(true), UUID.fromString("6be02cbc-852c-4faa-bf33-906be7f97374"));
    public static final DeferredHolder<Attribute, Attribute> VITALITY = registerAttribute("ironbounds_artefacts.attribute.vitality", (id) -> new RangedAttribute(id, 1.0D, 1.0D, 20D).setSyncable(true), UUID.fromString("6be02cbc-852c-49ea-bf33-906be7f97374"));
    public static final DeferredHolder<Attribute, Attribute> FOCUS = registerAttribute("ironbounds_artefacts.attribute.mind", (id) -> new RangedAttribute(id, 1.0D, 1.0D, 20D).setSyncable(true), UUID.fromString("6be02cbc-852c-49ea-bf83-906be7f97374"));


    public static DeferredHolder<Attribute, Attribute> registerAttribute(String name, Function<String, Attribute> attribute, UUID uuid) {
        DeferredHolder<Attribute, Attribute> registryObject = ATTRIBUTES.register(name, () -> attribute.apply(name));
        UUIDS.put(registryObject, uuid);
        return registryObject;
    }

    @SubscribeEvent
    static void registerRegistries(NewRegistryEvent event) {
        event.register(SubClassRegistry.SUBCLASS_REGISTRY);
        event.register(ClassRegistry.CLASS_REGISTRY);
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent e) {
        e.getTypes().forEach(entity -> ATTRIBUTES.getEntries().forEach(attribute -> e.add(entity, attribute)));
    }
}
