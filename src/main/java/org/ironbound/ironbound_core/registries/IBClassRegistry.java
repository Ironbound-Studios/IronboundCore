package org.ironbound.ironbound_core.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.*;

public class IBClassRegistry {
    public static final ResourceKey<Registry<IBClass>> CLASS_REGISTRY_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("main_classes_registry_keys"));
    public static final Registry<IBClass> CLASS_REGISTRY = new RegistryBuilder<>(CLASS_REGISTRY_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("class_registry"))
            .maxId(256)
            .create();

    public static final DeferredRegister<IBClass> IRONBOUND_CLASS_REGISTRY = DeferredRegister.create(CLASS_REGISTRY, "main_class_registry");

    public static final DeferredHolder<IBClass, RogueClass> ROGUE_CLASS = IRONBOUND_CLASS_REGISTRY.register("rogue_class", () -> RogueClass.instance);
    public static final DeferredHolder<IBClass, FighterClass> FIGHTER_CLASS = IRONBOUND_CLASS_REGISTRY.register("figher_class", () -> FighterClass.instance);
    public static final DeferredHolder<IBClass, HunterClass> HUNTER_CLASS = IRONBOUND_CLASS_REGISTRY.register("hunter_class", () -> HunterClass.instance);
    public static final DeferredHolder<IBClass, RangerClass> RANGER_CLASS = IRONBOUND_CLASS_REGISTRY.register("ranger_class", () -> RangerClass.instance);

    public static final DeferredHolder<IBClass, SorcererClass> SORCERER_CLASS = IRONBOUND_CLASS_REGISTRY.register("sorcerer_class", () -> SorcererClass.instance);
    public static final DeferredHolder<IBClass, WizardClass> WIZARD_CLASS = IRONBOUND_CLASS_REGISTRY.register("wizard_class", () -> WizardClass.instance);
    public static final DeferredHolder<IBClass, PriestClass> PRIEST_CLASS = IRONBOUND_CLASS_REGISTRY.register("priest_class", () -> PriestClass.instance);
    public static final DeferredHolder<IBClass, WarlockClass> WARLOCK_CLASS = IRONBOUND_CLASS_REGISTRY.register("warlock_player", () -> WarlockClass.instance);

    public static IBClass getMainFromLoc(ResourceLocation loc) {
        for (var key : IBClassRegistry.IRONBOUND_CLASS_REGISTRY.getEntries()) {
            if (key.get().classId.equals(loc)) {
                return key.get();
            }
        }
        return NoneClass.instance;
    }

    public static IBClass getMainFromLoc(String loc) {
        return getMainFromLoc(ResourceLocation.parse(loc));
    }

}
