package org.ironbound.ironbound_core.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import org.ironbound.ironbound_core.Ironbound;

import java.util.Optional;

public class IBDamageSourcesReg {
    public static final ResourceKey<DamageType> MADNESS = register("madness_damage");
    public static final ResourceKey<DamageType> DECAY = register("decay_damage");

    //public static final ResourceKey<DamageType> VOID_DAMAGE = register("void_damage");

    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Ironbound.prefix(name));
    }

    /**
     * @param entity : the target entity. Cannot be null.
     * @param key    : the damage key to be used. an example include the "VOID_DAMAGE" field present in this class.
     * @return       : returns a DamageType holder that can be used to apply damage.
     * Code taken from @link io.redspace.ironsspellbooks.damage.SpellDamageSource.getHolderFromResource()
     */
    public static Holder<DamageType> getFromKey(Entity entity, ResourceKey<DamageType> key) {
        Optional<Holder.Reference<DamageType>> option = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(key);
        if (option.isPresent()) {
            return option.get();
        } else {
            return entity.level().damageSources().genericKill().typeHolder();
        }
    }
}
