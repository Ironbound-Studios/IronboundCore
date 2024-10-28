package com.c446.ironbound_core.ironbound_classes.sub_classes;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.registries.AttachmentReg;
import com.c446.ironbound_core.registries.ClassRegistry;
import com.c446.ironbound_core.registries.SubClassRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.Objects;

import static com.c446.ironbound_core.registries.AttributeRegistry.INSIGHT;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Ironbound.MODID)
public class SubClassesEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onInflictPoison(MobEffectEvent.Added event) {
        if (event.getEffectSource() instanceof LivingEntity entity && ClassHelper.isClass(entity, ClassRegistry.ROGUE_CLASS.get()) && ClassHelper.isSubClass(entity, SubClassRegistry.PLAGUE_MASTER.get())) {
            if (Objects.requireNonNull(event.getEffectInstance()).getEffect().equals(MobEffects.POISON)) {
                var instance = event.getEffectInstance();
                event.getEntity().removeEffect(instance.getEffect());
                event.getEntity().addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration(), instance.getAmplifier() + 1));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onHit(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof LivingEntity living) {
            var attacked = event.getEntity();

            if (SubClassRegistry.PLAGUE_MASTER.get().canUseSecondPerk(living) && living.getAttributeValue(INSIGHT) > attacked.level().random.nextIntBetweenInclusive(1,20)) {
                attacked.addEffect(new MobEffectInstance(MobEffects.WITHER, 10,2));
            }
            else if (SubClassRegistry.ELDRITCH_KNIGHT.get().canUseSecondPerk(living) && living.getAttributeValue(INSIGHT) > attacked.level().random.nextIntBetweenInclusive(1,20)) {
                attacked.getData(AttachmentReg.STATUS_DATA).addTo(StatusTypes.MADNESS, (int) (living.getAttributeValue(INSIGHT)/2));
            }
        }
    }
}
