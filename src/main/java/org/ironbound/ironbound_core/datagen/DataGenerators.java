package org.ironbound.ironbound_core.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.ironbound.ironbound_core.Ironbound;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Ironbound.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        gen.addProvider(
                event.includeServer(),
                new ModSpellTagsProvider(out, SpellRegistry.SPELL_REGISTRY_KEY, provider, helper)
        );
    }

}
