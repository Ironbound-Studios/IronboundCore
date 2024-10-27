package com.c446.ironbound_core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.styly.arcanus.Arcanus;

import java.util.concurrent.CompletableFuture;

public class IronboundRitualRecipeProvider extends RecipeProvider {
    public IronboundRitualRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }


    @Override
    protected void buildRecipes(RecipeOutput output) {
        //Example recipe, if you want the pedestal to be empty use arcanus:air
        //new RitualRecipeBuilder(Items.NETHERITE_INGOT.getDefaultInstance(),
        // Arrays.asList(
        // ModItems.AIR.toStack(),
        // Items.DIAMOND.getDefaultInstance(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack(),
        // ModItems.AIR.toStack()
        // )
        // ).save(output,new ResourceLocation(Arcanus.MODID,"test_recipe2"));
    }
}
