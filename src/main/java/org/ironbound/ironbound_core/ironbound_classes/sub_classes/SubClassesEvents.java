package org.ironbound.ironbound_core.ironbound_classes.sub_classes;

import io.redspace.ironsspellbooks.api.events.*;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.mobs.SummonedSkeleton;
import io.redspace.ironsspellbooks.entity.mobs.SummonedZombie;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.data.attachements.StatusTypes;
import org.ironbound.ironbound_core.ironbound_classes.ClassHelper;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch.TimeWizard;
import org.ironbound.ironbound_core.registries.*;

import java.util.List;

import static io.redspace.ironsspellbooks.api.magic.MagicData.getPlayerMagicData;
import static io.redspace.ironsspellbooks.registries.DataAttachmentRegistry.MAGIC_DATA;
import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.GENERIC_DATA;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.INSIGHT;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Ironbound.MODID)
public class SubClassesEvents {
    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        var instance = event.getEffectInstance();
        if (event.getEntity() instanceof LivingEntity entity && instance != null) {
            if ((instance.getEffect().equals(MobEffects.WITHER) || instance.getEffect().equals(MobEffects.POISON))) {
                if (ClassHelper.isSubClass(event.getEntity().getLastAttacker(), IBSubClassRegistry.PLAGUE_MASTER.get())) {
                    //this will cause the game to crash in some cases! so we pray that never happens!
                    // Bah ! how could it ever happen !!! It's also clearly a *feature* *v*
                    instance.amplifier += (int) (ClassHelper.getLevel(event.getEntity().getLastAttacker()) / 10D) + 1;
                }
            } else if (ClassHelper.isSubClass(event.getEntity(), IBSubClassRegistry.CHRONURGIST.get()) && ClassHelper.getData(event.getEntity()).level() >= 18 && instance.getEffect().value().isBeneficial()) {
                instance.duration = (int) (instance.duration * (ClassHelper.getLevel(entity) / 10D + 0.5D));
            }
        }
    }


    @SubscribeEvent
    public static void onHit(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof LivingEntity living) {
            var attacked = event.getEntity();
            var attacker = event.getSource().getEntity();

            if (IBSubClassRegistry.PLAGUE_MASTER.get().canUseSecondPerk(living) && living.getAttributeValue(INSIGHT) > attacked.level().random.nextIntBetweenInclusive(1, 20)) {
                attacked.addEffect(new MobEffectInstance(MobEffects.WITHER, 10, 2));
            } else if (IBSubClassRegistry.ELDRITCH_KNIGHT.get().canUseSecondPerk(living) && living.getAttributeValue(INSIGHT) > attacked.level().random.nextIntBetweenInclusive(1, 20)) {
                attacked.getData(IBAttachmentRegistry.STATUS_DATA).addTo(StatusTypes.MADNESS, (int) (living.getAttributeValue(INSIGHT) / 2));
            } else if (ClassHelper.isClass((LivingEntity) attacker, IBClassRegistry.HUNTER_CLASS.get())) {
                // Hunter's Mark
                attacked.addEffect(new MobEffectInstance(IBMobEffectRegistry.REVEALING, 20, 1, true, true, true));
                if (attacked.hasEffect(MobEffects.INVISIBILITY) || attacked.hasEffect(MobEffectRegistry.TRUE_INVISIBILITY)) {
                    attacked.removeEffect(MobEffects.INVISIBILITY);
                    attacked.removeEffect(MobEffectRegistry.TRUE_INVISIBILITY);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Pre event) {
        var player = event.getEntity();
        // Undying Sorcerer ticking
        var world = player.level();
        var pos = player.getVehicle() instanceof Boat ? (new BlockPos((int) player.getX(), (int) Math.round(player.getY()), (int) player.getZ())).above() : new BlockPos((int) player.getX(), (int) Math.round(player.getY()), (int) player.getZ());
        if (ClassHelper.isClass(player, IBClassRegistry.SORCERER_CLASS.get())) {
            // DO MAIN SORCERER ABILITIES
        }
        if (ClassHelper.isClass(player, IBClassRegistry.ROGUE_CLASS.get())) {
            // DO MAIN ROGUE ABILITIES
            // DO BRIGHTNESS CHECK
            if (true) {
                player.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY, 1, 0));
            }
        }

        // Eldritch Knight stuff
        // Revel in Madness
        else if (ClassHelper.isSubClass(player, IBSubClassRegistry.ELDRITCH_KNIGHT.get())) {
            AABB radius = player.getBoundingBox().inflate(10, 10, 10);
            List<Entity> targets = world.getEntities(player, radius);

            for (Entity target : targets) {
                if (target instanceof LivingEntity livingTarget && livingTarget.hasEffect(IBMobEffectRegistry.MADNESS)) {
                    player.getAttribute(AttributeRegistry.ELDRITCH_SPELL_POWER).addOrUpdateTransientModifier(new AttributeModifier(Ironbound.prefix("eldritch_knight_eldr_power"), 0.2, AttributeModifier.Operation.ADD_VALUE));
                    System.out.println("Attributes: " + player.getAttribute(AttributeRegistry.ELDRITCH_SPELL_POWER).getAttribute());
                }
            }
        }
    }

    @SubscribeEvent
    public static void beginCast(SpellPreCastEvent event) {
        var data = getPlayerMagicData(event.getEntity());
        var caster = event.getEntity();
        if (TimeWizard.instance.canUseThirdPerk(event.getEntity()) && ClassHelper.isSubClass(caster, IBSubClassRegistry.CHRONURGIST.get()) && TimeWizard.instance.getReducedCastTimeSpells().contains(SpellRegistry.getSpell(event.getSpellId()).getSpellResource())) {
            caster.addEffect(new MobEffectInstance(IBMobEffectRegistry.CAST_TIME_REDUCTION_EFFECT, 5, 0));
        }
    }

    @SubscribeEvent
    public static void onGetLevel(ModifySpellLevelEvent event) {
        // increase level of TimeWizard compatible spells by correct amount.
        if (ClassHelper.isSubClass(event.getEntity(), IBSubClassRegistry.CHRONURGIST.get()) && ClassHelper.getLevel(event.getEntity()) > 12 && TimeWizard.instance.getBoostedSpells().contains(event.getSpell().getSpellResource())) {
            event.addLevels(TimeWizard.instance.getLevelBoost(event.getEntity()));
        }
    }

    @SubscribeEvent
    public static void endCast(SpellOnCastEvent event) {
        event.getEntity().removeEffect(IBMobEffectRegistry.CAST_TIME_REDUCTION_EFFECT);
    }

    // ARCANE EFFICIENCY PERK
    @SubscribeEvent
    public static void onManaChangeEvent(ChangeManaEvent event) {
        var spellCost = event.getOldMana() - event.getNewMana();
        if (spellCost >= 1000) {
            spellCost *= 0.25F;
            event.setNewMana(event.getNewMana() + spellCost);
        }
    }

    @SubscribeEvent
    public static void onSpellDamage(SpellDamageEvent event) {
        if (event.getSpellDamageSource().getEntity() instanceof LivingEntity source) {
            if (ClassHelper.isSubClass(source, IBSubClassRegistry.FIRE_WARLOCK.get())) {
                event.getEntity().invulnerableTime = 0;
                event.getEntity().hurt(new DamageSource(IBDamageSourcesReg.getFromKey(event.getEntity(), ISSDamageTypes.FIRE_MAGIC)),  event.getOriginalAmount() * 0.1F);
            }
        }
    }

    @SubscribeEvent
    public static void onSummonDeath(LivingDeathEvent event) {

        if (event.getEntity() instanceof Player player) {
            if (ClassHelper.isSubClass(player, IBSubClassRegistry.UNDYING.get()) && !player.getData(GENERIC_DATA).isEndlessImmortalityConsumed()) {
                event.setCanceled(true);
                player.getData(GENERIC_DATA).setEndlessImmortalityConsumed(true);
                player.getData(GENERIC_DATA).immortalityCooldown = 20 * 20 * 60;
            }
        }

        if (event.getEntity() instanceof SummonedZombie mob && ClassHelper.isSubClass(mob.getSummoner(), IBSubClassRegistry.UNDYING.get())) {
            var owner = mob.getSummoner();
            if (owner.hasData(MAGIC_DATA) && owner.getData(MAGIC_DATA).getMana() >= mob.getMaxHealth() / 2) {
                owner.getData(MAGIC_DATA).setMana(owner.getData(MAGIC_DATA).getMana() - mob.getMaxHealth() / 2);
                event.setCanceled(true);
                mob.heal(mob.getMaxHealth() / 2);
            }
        } else if (event.getEntity() instanceof SummonedSkeleton mob && ClassHelper.isSubClass(mob.getSummoner(), IBSubClassRegistry.UNDYING.get())) {
            var owner = mob.getSummoner();
            if (owner.hasData(MAGIC_DATA) && owner.getData(MAGIC_DATA).getMana() >= mob.getMaxHealth() / 2) {
                owner.getData(MAGIC_DATA).setMana(owner.getData(MAGIC_DATA).getMana() - mob.getMaxHealth() / 2);
                event.setCanceled(true);
                mob.heal(mob.getMaxHealth() / 2);
            }
        }
    }


}