package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.registries.AttachmentReg;
import com.c446.ironbound_core.registries.ClassRegistry;
import com.c446.ironbound_core.registries.SubClassRegistry;
import net.minecraft.DetectedVersion;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.c446.ironbound_core.registries.AttributeRegistry.INSIGHT;

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
    public static void onSunlight(PlayerTickEvent.Pre event) {
        var player = event.getEntity();
        // Undying Sorcerer ticking
        var world = player.level();
        {
            System.out.println("is player undead : "+ ClassHelper.isSubClass(event.getEntity(), SubClassRegistry.UNDYING.get()));
            if (ClassHelper.isSubClass(event.getEntity(), SubClassRegistry.UNDYING.get())){
                player.getFoodData().setFoodLevel(15);
                player.getFoodData().setSaturation(15);
                player.getFoodData().setExhaustion(5);
                var burn = new AtomicBoolean(false);
                if (!world.isClientSide && world.isDay()) {
                    BlockPos blockpos = player.getVehicle() instanceof Boat ? (new BlockPos((int) player.getX(), (int) Math.round(player.getY()), (int) player.getZ())).above() : new BlockPos((int) player.getX(), (int) Math.round(player.getY()), (int) player.getZ());
                    if (world.canSeeSky(blockpos)) {
                        burn.set(true);
                        System.out.println(burn.get());
                    }
                }
                // see if the player has a helmet
                player.getArmorSlots().forEach(a -> {
                    if (a.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot().equals(EquipmentSlot.HEAD)) {
                        burn.set(false);
                        a.getItem().setDamage(a, a.getDamageValue() + 1);
                    }
                });
                if (burn.get()) {
                    player.setRemainingFireTicks(60);
                }
            }
        }
        if (ClassHelper.isClass(player, ClassRegistry.ROGUE_CLASS.get())){

        }
    }
}