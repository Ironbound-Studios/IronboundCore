package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBSubClasses;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneIBSubClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.fighter.EldritchKnight;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SubClassRegistry {

    //        subClasses.put(EldritchKnight.instance.subClassID, EldritchKnight.instance);

    public static final ResourceKey<Registry<IBSubClasses>> SUBCLASS_REGISTRY_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("sub_classes_registry_keys"));
    public static final Registry<IBSubClasses> SUBCLASS_REGISTRY = new RegistryBuilder<>(SUBCLASS_REGISTRY_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("subclass_registry"))
            .maxId(256)
            .create();

    public static final DeferredRegister<IBSubClasses> IRONBOUND_SUBCLASS_REGISTRY = DeferredRegister.create(SUBCLASS_REGISTRY, "sub_class_registry");

    public static final DeferredHolder<IBSubClasses, EldritchKnight> ELDRITCH_KNIGHT = IRONBOUND_SUBCLASS_REGISTRY.register("eldritch_knight", ()-> EldritchKnight.instance);

    public static IBSubClasses getSubFromLoc(@NonNull ResourceLocation loc){
        for (var key : SubClassRegistry.IRONBOUND_SUBCLASS_REGISTRY.getEntries()){
            if (key.get().subClassID.equals(loc)){
                return (key.get());
            }
        }
        return NoneIBSubClass.instance;
    }

}
