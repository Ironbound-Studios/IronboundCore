package org.ironbound.ironbound_core.registries;

import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.ironbound.ironbound_core.entities.SimulacrumEntity;

public class IBEntitiesReg {
    EntityRegistry


    public static final DeferredHolder<EntityType<?>, EntityType<SimulacrumEntity>> ENTITY_DUMMY = registerEntity(
            "simulacrum",
            EntityType.Builder.<SimulacrumEntity>of(SimulacrumEntity::new, MobCategory.MISC)
                    .sized(1.0f, 2.0f)
                    .setTrackingRange(10)
                    .setShouldReceiveVelocityUpdates(true));
}
