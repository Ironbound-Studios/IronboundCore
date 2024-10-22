package com.example.examplemod.events;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import static com.example.examplemod.Ironbound.MODID;

@EventBusSubscriber(value = Dist.DEDICATED_SERVER, modid = MODID)
public class ServerEvents {
    @SubscribeEvent
    public static void test(MobStatusTriggeredEvent.Pre<LivingEntity> event){
        
    }

}
