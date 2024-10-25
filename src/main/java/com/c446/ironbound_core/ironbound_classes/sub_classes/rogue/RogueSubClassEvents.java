package com.c446.ironbound_core.ironbound_classes.sub_classes.rogue;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.main_classes.RogueClass;
import com.c446.ironbound_core.registries.AttachmentReg;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Objects;

@EventBusSubscriber(value = Dist.DEDICATED_SERVER, modid = Ironbound.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RogueSubClassEvents {
    @SubscribeEvent
    public static void onHitEvent(LivingDamageEvent.Pre event) {

        try {
            Objects.requireNonNull(event.getSource().getEntity()).getData(AttachmentReg.LEVEL_DATA).instances.forEach(
                    a -> {
                        if (a.classID.equals(RogueClass.instance.classId) && a.subClassID.equals(PlagueMaster.instance.subClassID) && a.getLevel() >= 5) {
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 5*a.getLevel(), 0));
                        }
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
