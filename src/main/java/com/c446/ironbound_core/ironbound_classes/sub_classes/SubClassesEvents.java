package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.eldritch.TimeWizard;
import com.c446.ironbound_core.registries.*;
import io.redspace.ironsspellbooks.api.events.ChangeManaEvent;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.mobs.SummonedSkeleton;
import io.redspace.ironsspellbooks.entity.mobs.SummonedZombie;
import io.redspace.ironsspellbooks.registries.DataAttachmentRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Objects;

import static com.c446.ironbound_core.registries.IBAttributeRegistry.INSIGHT;
import static io.redspace.ironsspellbooks.api.magic.MagicData.*;
import static io.redspace.ironsspellbooks.registries.DataAttachmentRegistry.MAGIC_DATA;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Ironbound.MODID)
public class SubClassesEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEffectSource() instanceof LivingEntity entity && event.getEffectSource() instanceof LivingEntity source) {
            if (ClassHelper.isClass(entity, IBClassRegistry.ROGUE_CLASS.get()) && ClassHelper.isSubClass(entity, IBSubClassRegistry.PLAGUE_MASTER.get())) {
                if (Objects.requireNonNull(event.getEffectInstance()).getEffect().equals(MobEffects.POISON)) {
                    var instance = event.getEffectInstance();
                    event.getEntity().removeEffect(instance.getEffect());
                    event.getEntity().addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration(), instance.getAmplifier() + 1));
                }
            }
            if (ClassHelper.isSubClass(source, IBSubClassRegistry.CHRONURGIST.get())) {
                if (Objects.requireNonNull(event.getEffectInstance()).getEffect() instanceof MagicMobEffect effect) {
                    entity.removeEffect(event.getEffectInstance().getEffect());
                    entity.addEffect(new MobEffectInstance(event.getEffectInstance().getEffect(), event.getEffectInstance().getDuration() * IBClass.getMastery(source) / 2));
                }
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
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
                attacked.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 1, true, true, true));
                if (attacked.hasEffect(MobEffects.INVISIBILITY) || attacked.hasEffect(MobEffectRegistry.TRUE_INVISIBILITY))
                {
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


    // Chronomancy mage stuff
    @SubscribeEvent
    public static void beginCast(SpellPreCastEvent event) {
        var data = getPlayerMagicData(event.getEntity());
        var caster = event.getEntity();
        if (TimeWizard.instance.canUseThirdPerk(event.getEntity()) && ClassHelper.isSubClass(caster, IBSubClassRegistry.CHRONURGIST.get()) && TimeWizard.instance.getReducedCastTimeSpells().contains(SpellRegistry.getSpell(event.getSpellId()).getSpellResource())) {
            caster.addEffect(new MobEffectInstance(IBMobEffectRegistry.CAST_TIME_REDUC, 5, 0));
        }
    }

    @SubscribeEvent
    public static void onGetLevel(ModifySpellLevelEvent event) {
        // increase level of TimeWizard compatible spells by correct amount.
        if (TimeWizard.instance.canUseSecondPerk(event.getEntity()) && ClassHelper.isSubClass(event.getEntity(), IBSubClassRegistry.CHRONURGIST.get()) && TimeWizard.instance.getLevelBoostedSpells().contains(event.getSpell().getSpellResource())) {
            event.addLevels(TimeWizard.instance.getLevelBoost(event.getEntity()));
        }
    }

    @SubscribeEvent
    public static void endCast(SpellOnCastEvent event) {
        event.getEntity().removeEffect(IBMobEffectRegistry.CAST_TIME_REDUC);
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
    public static void onSummonDeath(LivingDeathEvent event) {

        if (event.getEntity() instanceof SummonedZombie mob && ClassHelper.isSubClass(mob.getSummoner(), IBSubClassRegistry.UNDYING.get()) ) {
            var owner = mob.getSummoner();
            if (owner.hasData(MAGIC_DATA) && owner.getData(MAGIC_DATA).getMana() >= mob.getMaxHealth() / 2){
                owner.getData(MAGIC_DATA).setMana(owner.getData(MAGIC_DATA).getMana() - mob.getMaxHealth() / 2);
                event.setCanceled(true);
                mob.heal(mob.getMaxHealth() / 2);
            }
        } else if (event.getEntity() instanceof SummonedSkeleton mob && ClassHelper.isSubClass(mob.getSummoner(), IBSubClassRegistry.UNDYING.get())) {
            var owner = mob.getSummoner();
            if (owner.hasData(MAGIC_DATA) && owner.getData(MAGIC_DATA).getMana() >= mob.getMaxHealth() / 2){
                owner.getData(MAGIC_DATA).setMana(owner.getData(MAGIC_DATA).getMana() - mob.getMaxHealth() / 2);
                event.setCanceled(true);
                mob.heal(mob.getMaxHealth() / 2);
            }
        }
    }
}