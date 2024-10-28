package com.c446.ironbound_core.datagen;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.recipe.RitualRecipeBuilder;
import org.styly.arcanus.registry.ModItems;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class IronboundRitualRecipeProvider extends RecipeProvider {
    public IronboundRitualRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }


    @Override
    protected void buildRecipes(RecipeOutput output) {
        new RitualRecipeBuilder(new ItemStack(ItemRegistry.UPGRADE_POTION1.get()),
         Arrays.asList(
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack(),
         ModItems.AIR.toStack()
         )
         ).save(output, Ironbound.prefix("upgrade_pot_one"));
    }
}
