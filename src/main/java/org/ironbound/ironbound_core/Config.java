package org.ironbound.ironbound_core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Set;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Ironbound.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    static final ModConfigSpec SPEC = BUILDER.build();
    private static final ModConfigSpec.BooleanValue IS_IFRAME_SKIP_ON = BUILDER
            .comment("Should IFrames be skipped?")
            .define("iframe_disabled", false);
    private static final ModConfigSpec.IntValue IFRAME_COUNT = BUILDER
            .comment("how many iframes to leave ?")
            .defineInRange("iframe_count", 0, 0, 100000);
    public static boolean iframeSkipping;
    public static int iframeCount;
    public static Set<Item> items;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        iframeSkipping = IS_IFRAME_SKIP_ON.get();
        iframeCount = IFRAME_COUNT.get();

    }
}
