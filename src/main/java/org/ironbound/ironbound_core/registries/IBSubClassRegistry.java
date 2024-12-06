package org.ironbound.ironbound_core.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBSubClass;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.blood.UndyingSorcerer;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch.EldritchKnight;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch.TimeWizard;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.fire.FireWarlock;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.nature.PlagueMaster;

public class IBSubClassRegistry {

    //        subClasses.put(EldritchKnight.instance.subClassID, EldritchKnight.instance);

    public static final ResourceKey<Registry<IBSubClass>> SUBCLASS_REGISTRY_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("sub_classes_registry_keys"));
    public static final Registry<IBSubClass> SUBCLASS_REGISTRY = new RegistryBuilder<>(SUBCLASS_REGISTRY_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("subclass_registry"))
            .maxId(256)
            .create();

    public static final DeferredRegister<IBSubClass> IRONBOUND_SUBCLASS_REGISTRY = DeferredRegister.create(SUBCLASS_REGISTRY, "sub_class_registry");

    public static final DeferredHolder<IBSubClass, EldritchKnight> ELDRITCH_FIGHTER = IRONBOUND_SUBCLASS_REGISTRY.register("eldritch_knight", () -> EldritchKnight.instance);
    public static final DeferredHolder<IBSubClass, PlagueMaster> NATURE_FIGHTER = IRONBOUND_SUBCLASS_REGISTRY.register("plague_master", () -> PlagueMaster.instance);
    public static final DeferredHolder<IBSubClass, UndyingSorcerer> BLOOD_SORCERER = IRONBOUND_SUBCLASS_REGISTRY.register("undying", () -> UndyingSorcerer.instance);
    public static final DeferredHolder<IBSubClass, TimeWizard> CHRONURGIST = IRONBOUND_SUBCLASS_REGISTRY.register("chronomancer", () -> TimeWizard.instance);
    public static final DeferredHolder<IBSubClass, FireWarlock> FIRE_WARLOCK = IRONBOUND_SUBCLASS_REGISTRY.register("fire_warlock", () -> FireWarlock.instance);
    public static final DeferredHolder<IBSubClass, FireWarlock> FIRE_WARLOCK = IRONBOUND_SUBCLASS_REGISTRY.register("fire_warlock", () -> FireWarlock.instance);


    public static IBSubClass getSubFromLoc(@NonNull ResourceLocation loc) {
        for (var key : IBSubClassRegistry.IRONBOUND_SUBCLASS_REGISTRY.getEntries()) {
            if (key.get().subClassID.equals(loc)) {
                return (key.get());
            }
        }
        return NoneSubClass.instance;
    }

    public static IBSubClass getSubFromLoc(@NonNull String loc) {
        return getSubFromLoc(ResourceLocation.parse(loc));
    }
}
