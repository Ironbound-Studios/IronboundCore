package org.ironbound.ironbound_core;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.ironbound.ironbound_core.registries.IBModSetup;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Ironbound.MODID)
public class Ironbound {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ironbound_core";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "ironbound_core" namespace

    public Ironbound(IEventBus modEventBus, ModContainer modContainer) {
        IBModSetup.register(modEventBus);
//        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);

        modEventBus.addListener(this::setup);
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public void setup(final FMLCommonSetupEvent event) {
//        event.enqueueWork(ArsNouveauRegistry::postInit);
    }
}
