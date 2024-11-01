package org.ironbound.ironbound_core.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.ClassHelper;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.registries.IBMobEffectRegistry;
import org.ironbound.ironbound_core.registries.IBSubClassRegistry;
import top.theillusivec4.curios.api.SlotContext;

public class WizardItem extends ClassItem {
    public WizardItem(Properties properties, IBClass ibClass) {
        super(properties, ibClass);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        var defaultAttributes = super.getAttributeModifiers(slotContext, id, stack);

        var entity = slotContext.entity();
        if (entity == null) {
            return defaultAttributes;
        }
        HashMultimap<Holder<Attribute>, AttributeModifier> wizardSpecific = HashMultimap.create();


        wizardSpecific.put(AttributeRegistry.MANA_REGEN, new AttributeModifier(Ironbound.prefix("extra_class_mana_regen"),
                Math.min(1, 1 / (Math.max(1, entity.getAttributeValue(Attributes.ARMOR) + entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS)) / 4)), AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        defaultAttributes.putAll(wizardSpecific);
        return defaultAttributes;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() != null) {
            var entity = slotContext.entity();
            if (entity.tickCount % 5 != 0) {
                if (!entity.level.isClientSide && ClassHelper.isSubClass(entity, IBSubClassRegistry.CHRONURGIST.get())) {
                    var day = (entity.level.getGameTime() / (20 * 20 * 60) % 4);
                    switch ((int) day) {
                        case 1 ->
                                entity.addEffect(new MobEffectInstance(IBMobEffectRegistry.COOLDOWN_REDUCTION_EFFECT, 10, 0, true, true, true));
                        case 2 ->
                                entity.addEffect(new MobEffectInstance(IBMobEffectRegistry.CAST_TIME_REDUCTION_EFFECT, 10, 0, true, true, true));
                        case 3 ->
                                entity.addEffect(new MobEffectInstance(IBMobEffectRegistry.MANA_REGEN_EFFECT, 10, 0, true, true, true));
                    }
                }
            }
        }
        super.curioTick(slotContext, stack);
    }
}
