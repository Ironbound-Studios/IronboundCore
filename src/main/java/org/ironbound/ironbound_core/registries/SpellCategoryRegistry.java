package org.ironbound.ironbound_core.registries;

import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.ironbound.ironbound_core.Ironbound;

import java.util.ArrayList;
import java.util.Objects;

public class SpellCategoryRegistry {
    public static final ResourceKey<Registry<AbstractSpell>> OFFENSIVE_SPELLS_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("offensive_spells"));
    public static final Registry<AbstractSpell> OFFENSIVE_SPELL_REGISTRY = new RegistryBuilder<>(OFFENSIVE_SPELLS_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("offensive_spell_registry"))
            .maxId(256)
            .create();
    public static final DeferredRegister<AbstractSpell> OFFENSIVE_SPELLS = DeferredRegister.create(OFFENSIVE_SPELL_REGISTRY, "offensive_spells");

    public static final ResourceKey<Registry<AbstractSpell>> DEFENSIVE_SPELLS_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("defensive_spells"));
    public static final Registry<AbstractSpell> DEFENSIVE_SPELLS_REGISTRY = new RegistryBuilder<>(DEFENSIVE_SPELLS_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("defensive_spells_registry"))
            .maxId(256)
            .create();
    public static final DeferredRegister<AbstractSpell> DEFENSIVE_SPELLS = DeferredRegister.create(DEFENSIVE_SPELLS_REGISTRY, "defensive_spells");

    public static final ResourceKey<Registry<AbstractSpell>> MOVEMENT_SPELLS_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("movement_spells"));
    public static final Registry<AbstractSpell> MOVEMENT_SPELLS_REGISTRY = new RegistryBuilder<>(OFFENSIVE_SPELLS_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("movement_spell_registry"))
            .maxId(256)
            .create();
    public static final DeferredRegister<AbstractSpell> MOVEMENT_SPELLS = DeferredRegister.create(OFFENSIVE_SPELL_REGISTRY, "movement_spell_registry");

    public static final ResourceKey<Registry<AbstractSpell>> UTIL_SPELLS_KEY = ResourceKey.createRegistryKey(Ironbound.prefix("util_spells"));
    public static final Registry<AbstractSpell> UTIL_SPELLS_REGISTRY = new RegistryBuilder<>(OFFENSIVE_SPELLS_KEY)
            .sync(true)
            .defaultKey(Ironbound.prefix("util_spell_registry"))
            .maxId(256)
            .create();
    public static final DeferredRegister<AbstractSpell> UTIL_SPELLS = DeferredRegister.create(OFFENSIVE_SPELL_REGISTRY, "util_spell_registry");

    static{
        DEFENSIVE_SPELLS.register(SpellRegistry.ABYSSAL_SHROUD_SPELL.get().getSpellId(), SpellRegistry.ABYSSAL_SHROUD_SPELL);
        DEFENSIVE_SPELLS.register(SpellRegistry.OAKSKIN_SPELL.get().getSpellId(), SpellRegistry.OAKSKIN_SPELL);
        DEFENSIVE_SPELLS.register(SpellRegistry.EVASION_SPELL.get().getSpellId(), SpellRegistry.EVASION_SPELL);

        OFFENSIVE_SPELLS.register(SpellRegistry.ACID_ORB_SPELL.get().getSpellId(), SpellRegistry.ACID_ORB_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.ACUPUNCTURE_SPELL.get().getSpellId(), SpellRegistry.ACUPUNCTURE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.BALL_LIGHTNING_SPELL.get().getSpellId(), SpellRegistry.BALL_LIGHTNING_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.BLAZE_STORM_SPELL.get().getSpellId(), SpellRegistry.BLAZE_STORM_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.BLACK_HOLE_SPELL.get().getSpellId(), SpellRegistry.BLACK_HOLE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.BLOOD_SLASH_SPELL.get().getSpellId(), SpellRegistry.BLOOD_SLASH_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.BLOOD_NEEDLES_SPELL.get().getSpellId(), SpellRegistry.BLOOD_NEEDLES_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.CHAIN_CREEPER_SPELL.get().getSpellId(), SpellRegistry.CHAIN_CREEPER_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.CONE_OF_COLD_SPELL.get().getSpellId(), SpellRegistry.CONE_OF_COLD_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.CHAIN_LIGHTNING_SPELL.get().getSpellId(), SpellRegistry.CHAIN_LIGHTNING_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.DEVOUR_SPELL.get().getSpellId(), SpellRegistry.DEVOUR_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.DIVINE_SMITE_SPELL.get().getSpellId(), SpellRegistry.DIVINE_SMITE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.DRAGON_BREATH_SPELL.get().getSpellId(), SpellRegistry.DRAGON_BREATH_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.EARTHQUAKE_SPELL.get().getSpellId(), SpellRegistry.EARTHQUAKE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.ELECTROCUTE_SPELL.get().getSpellId(), SpellRegistry.ELECTROCUTE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.ECHOING_STRIKES_SPELL.get().getSpellId(), SpellRegistry.ECHOING_STRIKES_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.ELDRITCH_BLAST_SPELL.get().getSpellId(), SpellRegistry.ELDRITCH_BLAST_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FANG_WARD_SPELL.get().getSpellId(), SpellRegistry.FANG_WARD_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FANG_STRIKE_SPELL.get().getSpellId(), SpellRegistry.FANG_STRIKE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FIREBALL_SPELL.get().getSpellId(), SpellRegistry.FIREBALL_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FIRE_BREATH_SPELL.get().getSpellId(), SpellRegistry.FIRE_BREATH_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FIRECRACKER_SPELL.get().getSpellId(), SpellRegistry.FIRECRACKER_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FLAMING_BARRAGE_SPELL.get().getSpellId(), SpellRegistry.FLAMING_BARRAGE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FLAMING_STRIKE_SPELL.get().getSpellId(), SpellRegistry.FLAMING_STRIKE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FROSTWAVE_SPELL.get().getSpellId(), SpellRegistry.FROSTWAVE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.FIREBOLT_SPELL.get().getSpellId(), SpellRegistry.FIREBOLT_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.GUST_SPELL.get().getSpellId(), SpellRegistry.GUST_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.GUIDING_BOLT_SPELL.get().getSpellId(), SpellRegistry.GUIDING_BOLT_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.HEAT_SURGE_SPELL.get().getSpellId(), SpellRegistry.HEAT_SURGE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.BLIGHT_SPELL.get().getSpellId(), SpellRegistry.BLIGHT_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.LIGHTNING_BOLT_SPELL.get().getSpellId(), SpellRegistry.LIGHTNING_BOLT_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.LIGHTNING_LANCE_SPELL.get().getSpellId(), SpellRegistry.LIGHTNING_LANCE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.LOB_CREEPER_SPELL.get().getSpellId(), SpellRegistry.LOB_CREEPER_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.MAGIC_ARROW_SPELL.get().getSpellId(), SpellRegistry.MAGIC_ARROW_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.MAGIC_MISSILE_SPELL.get().getSpellId(), SpellRegistry.MAGIC_MISSILE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.MAGMA_BOMB_SPELL.get().getSpellId(), SpellRegistry.MAGMA_BOMB_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.POISON_ARROW_SPELL.get().getSpellId(), SpellRegistry.POISON_ARROW_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.POISON_BREATH_SPELL.get().getSpellId(), SpellRegistry.POISON_BREATH_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.POISON_SPLASH_SPELL.get().getSpellId(), SpellRegistry.POISON_SPLASH_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.RAY_OF_SIPHONING_SPELL.get().getSpellId(), SpellRegistry.RAY_OF_SIPHONING_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.ROOT_SPELL.get().getSpellId(), SpellRegistry.RAY_OF_FROST_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.RAISE_DEAD_SPELL.get().getSpellId(), SpellRegistry.RAISE_DEAD_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.RAY_OF_FROST_SPELL.get().getSpellId(), SpellRegistry.RAY_OF_FROST_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.THUNDERSTORM_SPELL.get().getSpellId(), SpellRegistry.THUNDERSTORM_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.TELEKINESIS_SPELL.get().getSpellId(), SpellRegistry.TELEKINESIS_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.WISP_SPELL.get().getSpellId(), SpellRegistry.WISP_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.WALL_OF_FIRE_SPELL.get().getSpellId(), SpellRegistry.WALL_OF_FIRE_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.WITHER_SKULL_SPELL.get().getSpellId(), SpellRegistry.WITHER_SKULL_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.SUMMON_POLAR_BEAR_SPELL.get().getSpellId(), SpellRegistry.WITHER_SKULL_SPELL);
        OFFENSIVE_SPELLS.register(SpellRegistry.SUMMON_VEX_SPELL.get().getSpellId(), SpellRegistry.WITHER_SKULL_SPELL);

        MOVEMENT_SPELLS.register(SpellRegistry.TELEPORT_SPELL.get().getSpellId(), SpellRegistry.TELEPORT_SPELL);
        MOVEMENT_SPELLS.register(SpellRegistry.BLOOD_STEP_SPELL.get().getSpellId(), SpellRegistry.TELEPORT_SPELL);
        MOVEMENT_SPELLS.register(SpellRegistry.FROST_STEP_SPELL.get().getSpellId(), SpellRegistry.TELEPORT_SPELL);
        MOVEMENT_SPELLS.register(SpellRegistry.ASCENSION_SPELL.get().getSpellId(), SpellRegistry.TELEPORT_SPELL);
        MOVEMENT_SPELLS.register(SpellRegistry.BURNING_DASH_SPELL.get().getSpellId(), SpellRegistry.TELEPORT_SPELL);

        UTIL_SPELLS.register(SpellRegistry.CHARGE_SPELL.get().getSpellId(), SpellRegistry.CHARGE_SPELL);
        UTIL_SPELLS.register(SpellRegistry.HASTE_SPELL.get().getSpellId(), SpellRegistry.HASTE_SPELL);
        UTIL_SPELLS.register(SpellRegistry.HEARTSTOP_SPELL.get().getSpellId(), SpellRegistry.HEARTSTOP_SPELL);
        UTIL_SPELLS.register(SpellRegistry.HEAL_SPELL.get().getSpellId(), SpellRegistry.HEAL_SPELL);
        UTIL_SPELLS.register(SpellRegistry.GREATER_HEAL_SPELL.get().getSpellId(), SpellRegistry.GREATER_HEAL_SPELL);
        UTIL_SPELLS.register(SpellRegistry.HEALING_CIRCLE_SPELL.get().getSpellId(), SpellRegistry.HEALING_CIRCLE_SPELL);
        UTIL_SPELLS.register(SpellRegistry.FORTIFY_SPELL.get().getSpellId(), SpellRegistry.FORTIFY_SPELL);
        UTIL_SPELLS.register(SpellRegistry.BLESSING_OF_LIFE_SPELL.get().getSpellId(), SpellRegistry.BLESSING_OF_LIFE_SPELL);
    }

    public static ArrayList<AbstractSpell> getOffensiveSpells(Player player) {
        var spells = new SpellSelectionManager(player);
        var list = new ArrayList<AbstractSpell>();
        spells.getAllSpells().forEach(a -> {
                    SpellCategoryRegistry.OFFENSIVE_SPELLS.getEntries()
                            .forEach(b -> {
                                        if (Objects.equals(b.value().getSpellId(), a.spellData.getSpell().getSpellId())) {
                                            list.add(b.value());
                                        }
                                    }
                            );
                }
        );
        return list;
    }

    public static ArrayList<AbstractSpell> getDefensiveSpells(Player player) {
        var spells = new SpellSelectionManager(player);
        var list = new ArrayList<AbstractSpell>();
        spells.getAllSpells().forEach(a -> {
                    SpellCategoryRegistry.DEFENSIVE_SPELLS.getEntries()
                            .forEach(b -> {
                                        if (Objects.equals(b.value().getSpellId(), a.spellData.getSpell().getSpellId())) {
                                            list.add(b.value());
                                        }
                                    }
                            );
                }
        );
        return list;
    }

    public static ArrayList<AbstractSpell> getUtilSpells(Player player) {
        var spells = new SpellSelectionManager(player);
        var list = new ArrayList<AbstractSpell>();
        spells.getAllSpells().forEach(a -> {
                    SpellCategoryRegistry.UTIL_SPELLS.getEntries()
                            .forEach(b -> {
                                        if (Objects.equals(b.value().getSpellId(), a.spellData.getSpell().getSpellId())) {
                                            list.add(b.value());
                                        }
                                    }
                            );
                }
        );
        return list;
    }

    public static ArrayList<AbstractSpell> getMovementSpells(Player player) {
        var spells = new SpellSelectionManager(player);
        var list = new ArrayList<AbstractSpell>();
        spells.getAllSpells().forEach(a -> {
                    SpellCategoryRegistry.MOVEMENT_SPELLS.getEntries()
                            .forEach(b -> {
                                        if (Objects.equals(b.value().getSpellId(), a.spellData.getSpell().getSpellId())) {
                                            list.add(b.value());
                                        }
                                    }
                            );
                }
        );
        return list;
    }




}
