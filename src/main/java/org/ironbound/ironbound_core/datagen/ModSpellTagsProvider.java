package org.ironbound.ironbound_core.datagen;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.ironbound.ironbound_core.Ironbound;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModSpellTagsProvider extends TagsProvider<AbstractSpell> {


    protected ModSpellTagsProvider(PackOutput pOutput, ResourceKey<? extends Registry<AbstractSpell>> pRegistryKey, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pRegistryKey, pLookupProvider, Ironbound.MODID, existingFileHelper);
    }

    protected ModSpellTagsProvider(PackOutput pOutput, ResourceKey<? extends Registry<AbstractSpell>> pRegistryKey, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<AbstractSpell>> pParentProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pRegistryKey, pLookupProvider, pParentProvider, Ironbound.MODID, existingFileHelper);
    }

    public ResourceKey<AbstractSpell> getKey(AbstractSpell spell) {
        return ResourceKey.create(SpellRegistry.SPELL_REGISTRY_KEY, ResourceLocation.parse(spell.getSpellId()));
    }

    public ResourceKey<AbstractSpell> getKey(String spell) {
        return ResourceKey.create(SpellRegistry.SPELL_REGISTRY_KEY, ResourceLocation.parse(spell));
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(Tags.SpellTags.UTILITY_SPELL)
                .add(getKey(SpellRegistry.CHARGE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.HASTE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.HEARTSTOP_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.HEAL_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.GREATER_HEAL_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.HEALING_CIRCLE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FORTIFY_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.BLESSING_OF_LIFE_SPELL.get().getSpellId()));

        tag(Tags.SpellTags.MOUVEMENT_SPELL)
                .add(getKey(SpellRegistry.TELEPORT_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.BLOOD_STEP_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FROST_STEP_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.ASCENSION_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.BURNING_DASH_SPELL.get().getSpellId()));

        tag(Tags.SpellTags.DEFENSIVE_SPELL)
                .add(getKey(SpellRegistry.ABYSSAL_SHROUD_SPELL.get()))
                .add(getKey(SpellRegistry.OAKSKIN_SPELL.get()))
                .add(getKey(SpellRegistry.EVASION_SPELL.get()));

        tag(Tags.SpellTags.OFFENSIVE_SPELL)
                .add(getKey(SpellRegistry.ACID_ORB_SPELL.get()))
                .add(getKey(SpellRegistry.ACUPUNCTURE_SPELL.get()))
                .add(getKey(SpellRegistry.BALL_LIGHTNING_SPELL.get()))
                .add(getKey(SpellRegistry.BLAZE_STORM_SPELL.get()))
                .add(getKey(SpellRegistry.BLACK_HOLE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.BLOOD_SLASH_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.BLOOD_NEEDLES_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.CHAIN_CREEPER_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.CONE_OF_COLD_SPELL.get().getSpellId())).add(getKey(SpellRegistry.FIREBALL_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FIRE_BREATH_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FIRECRACKER_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FLAMING_BARRAGE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FLAMING_STRIKE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FROSTWAVE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.FIREBOLT_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.GUST_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.GUIDING_BOLT_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.HEAT_SURGE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.BLIGHT_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.LIGHTNING_BOLT_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.LIGHTNING_LANCE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.LOB_CREEPER_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.MAGIC_ARROW_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.MAGIC_MISSILE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.MAGMA_BOMB_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.POISON_ARROW_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.POISON_BREATH_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.POISON_SPLASH_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.RAY_OF_SIPHONING_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.RAY_OF_FROST_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.RAISE_DEAD_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.THUNDERSTORM_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.TELEKINESIS_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.WISP_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.WALL_OF_FIRE_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.WITHER_SKULL_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.SUMMON_POLAR_BEAR_SPELL.get().getSpellId()))
                .add(getKey(SpellRegistry.SUMMON_VEX_SPELL.get().getSpellId()));
    }
}
