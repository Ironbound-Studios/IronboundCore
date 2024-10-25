package com.c446.ironbound_core.events;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.datagen.IronboundRitualRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Ironbound.MODID,bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // other providers here
        generator.addProvider(
                event.includeServer(),
                new IronboundRitualRecipeProvider(output, lookupProvider)
        );
    }
}
