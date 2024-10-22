package com.example.examplemod.registries;

import com.example.examplemod.Ironbound;
import com.example.examplemod.capabilities.StatusCap;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityRegistry {
    public static final EntityCapability<StatusCap, Void> STATUSES = EntityCapability.createVoid(Ironbound.prefix("statuses"), StatusCap.class);


//    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
//        event.registerEntity(STATUSES, EntityType.PLAYER, (player, ctx) -> new StatusCap(player));
//    }
}
