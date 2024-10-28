package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.registries.*;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
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
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Objects;

import static com.c446.ironbound_core.registries.AttributeRegistry.INSIGHT;
import static io.redspace.ironsspellbooks.api.magic.MagicData.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Ironbound.MODID)
public class SubClassesEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onInflictPoison(MobEffectEvent.Added event) {
        if (event.getEffectSource() instanceof LivingEntity entity && ClassHelper.isClass(entity, ClassRegistry.ROGUE_CLASS.get()) && ClassHelper.isSubClass(entity, SubClassRegistry.PLAGUE_MASTER.get())) {
            if (Objects.requireNonNull(event.getEffectInstance()).getEffect().equals(MobEffects.POISON)) {
                var instance = event.getEffectInstance();
                event.getEntity().removeEffect(instance.getEffect());
                event.getEntity().addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration(), instance.getAmplifier() + 1));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHit(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof LivingEntity living) {
            var attacked = event.getEntity();

            if (SubClassRegistry.PLAGUE_MASTER.get().canUseSecondPerk(living) && living.getAttributeValue(INSIGHT) > attacked.level().random.nextIntBetweenInclusive(1, 20)) {
                attacked.addEffect(new MobEffectInstance(MobEffects.WITHER, 10, 2));
            } else if (SubClassRegistry.ELDRITCH_KNIGHT.get().canUseSecondPerk(living) && living.getAttributeValue(INSIGHT) > attacked.level().random.nextIntBetweenInclusive(1, 20)) {
                attacked.getData(AttachmentReg.STATUS_DATA).addTo(StatusTypes.MADNESS, (int) (living.getAttributeValue(INSIGHT) / 2));
            }
        }
    }


    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Pre event) {
        var player = event.getEntity();
        // Undying Sorcerer ticking
        var world = player.level();
        var pos = player.getVehicle() instanceof Boat ? (new BlockPos((int) player.getX(), (int) Math.round(player.getY()), (int) player.getZ())).above() : new BlockPos((int) player.getX(), (int) Math.round(player.getY()), (int) player.getZ());
        if (ClassHelper.isClass(player, ClassRegistry.SORCERER_CLASS.get())) {
            // DO MAIN SORCERER ABILITIES
        }
        if (ClassHelper.isClass(player, ClassRegistry.ROGUE_CLASS.get())) {
            // DO MAIN ROGUE ABILITIES
            // DO BRIGHTNESS CHECK
            if (true) {
                player.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY, 1, 0));
            }
        }


        // Chronurgist stuff
        // Night Blessing
        if (ClassHelper.isSubClass(player, SubClassRegistry.CHRONURGIST.get())) {
            if (world.isNight()) {
                player.addEffect(new MobEffectInstance(EffectRegistries.NIGHT_BLESSING, 10, 0));
            }
        }


        // Eldritch Knight stuff
        // Revel in Madness
        else if (ClassHelper.isSubClass(player, SubClassRegistry.ELDRITCH_KNIGHT.get())) {
            AABB radius = player.getBoundingBox().inflate(10, 10, 10);
            List<Entity> targets = world.getEntities(player, radius);

            for (Entity target : targets) {
                if (target instanceof LivingEntity livingTarget && livingTarget.hasEffect(EffectRegistries.MADNESS)) {
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
        if (ClassHelper.isSubClass(caster, SubClassRegistry.CHRONURGIST.get())) {
            caster.addEffect(new MobEffectInstance(EffectRegistries.CAST_TIME_REDUC, 5,0));
        }
        //if (SpellRegistry.getSpell(event.getSpellId())){}
    }

    @SubscribeEvent
    public static void endCast(SpellOnCastEvent event){
            event.getEntity().removeEffect(EffectRegistries.CAST_TIME_REDUC);
    }
}