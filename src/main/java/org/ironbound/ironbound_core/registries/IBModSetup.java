package org.ironbound.ironbound_core.registries;

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

import static org.ironbound.ironbound_core.Ironbound.MODID;

public class IBModSetup {
    public static void register(IEventBus eventBus) {
        IBItemRegistry.ITEMS.register(eventBus);
        IBAttributeRegistry.ATTRIBUTES.register(eventBus);
        IBMobEffectRegistry.register(eventBus);
        IBAttachmentRegistry.ATTACHMENT_TYPE_DEFERRED_REGISTER.register(eventBus);
        IBClassRegistry.IRONBOUND_CLASS_REGISTRY.register(eventBus);
        IBSubClassRegistry.IRONBOUND_SUBCLASS_REGISTRY.register(eventBus);
        ModCreativeTabReg.CREATIVE_MOD_TABS.register(eventBus);
        IBComponentRegistry.register(eventBus);
        //ModIngredientTypeRegistry.INGREDIENT_TYPES.register(eventBus);
    }

    public IBModSetup(IEventBus modEventBus, ModContainer modContainer) {
        IBModSetup.register(modEventBus);
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
                            entries.accept(new ItemStack(IBItemRegistry.FIGHTER_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.HUNTER_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.ROGUE_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.RANGER_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.SORCERER_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.PRIEST_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.WARLOCK_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.WIZARD_CURIO));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION1));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION2));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION3));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION4));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION5));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION6));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION7));
                            entries.accept(new ItemStack(IBItemRegistry.UPGRADE_POTION8));

                        })
                        .build()
        );
    }
}

