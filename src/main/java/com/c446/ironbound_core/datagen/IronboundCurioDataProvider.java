package com.c446.ironbound_core.datagen;


import com.c446.ironbound_core.Ironbound;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class IronboundCurioDataProvider extends CuriosDataProvider {
    public IronboundCurioDataProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(Ironbound.MODID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        ResourceLocation curioValidator = ResourceLocation.fromNamespaceAndPath("curios", "tag");

        this.createSlot("head").size(1);
        this.createSlot("ring").size(2);
        this.createSlot("hands").size(1);
        this.createSlot("necklace").size(1);
        this.createSlot("tarot_slot")
                .addValidator(curioValidator)
                .icon(ResourceLocation.fromNamespaceAndPath("curios", "slot/empty_curio_slot"))
                .order(1)
                .size(1);

        this.createEntities("ironbound_curios").addPlayer()
                .addSlots("head", "ring", "hands", "necklace", "tarot_slot");
    }
}
