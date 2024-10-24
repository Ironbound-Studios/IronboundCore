package com.c446.ironbound_core.registries;

import io.redspace.ironsspellbooks.registries.CreativeTabRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.c446.ironbound_core.Ironbound.MODID;


public class ModSetup {
    public static void register(IEventBus eventBus) {
        ItemRegistry.ITEMS.register(eventBus);
        AttributeRegistry.ATTRIBUTES.register(eventBus);
        EffectsRegistry.EFFECTS.register(eventBus);
        EffectRegistries.register(eventBus);
        AttacmentReg.ATTACHMENT_TYPE_DEFERRED_REGISTER.register(eventBus);
        //ArmorMaterials.MATERIALS.register(eventBus);
        ModCreativeTabReg.CREATIVE_MOD_TABS.register(eventBus);
        //ModIngredientTypeRegistry.INGREDIENT_TYPES.register(eventBus);
    }

    public ModSetup(IEventBus modEventBus, ModContainer modContainer) {
        ModSetup.register(modEventBus);
        //modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);

        modEventBus.addListener(this::setup);
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public void setup(final FMLCommonSetupEvent event) {
        // DO OTHER MODS CONFIG
    }


    protected static class ModCreativeTabReg {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> THINGS = CREATIVE_MOD_TABS.register("ironbound_artefacts", () ->
                CreativeModeTab.builder()
                        .withTabsBefore(CreativeTabRegistry.EQUIPMENT_TAB.getKey())
                        .title(Component.translatable("tab.ironbound.main"))
                        .icon(() -> new ItemStack(io.redspace.ironsspellbooks.registries.ItemRegistry.RAW_MITHRIL))
                        .displayItems((enabledFeatures, entries) -> {
                        })
                        .build()
        );
    }
}

