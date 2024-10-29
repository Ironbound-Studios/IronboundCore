package com.c446.ironbound_core.items;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.WizardClass;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.spells.ender.StarfallSpell;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
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
                Math.min(1,1 / (Math.max(1, entity.getAttributeValue(Attributes.ARMOR) + entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS))/4)), AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        defaultAttributes.putAll(wizardSpecific);
        return defaultAttributes;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() != null){
            var entity = slotContext.entity();
            if (entity.level() instanceof ServerLevel server){
                //TODO : do "Time-Sensitive" subclass ability.
            }
        }



        super.curioTick(slotContext, stack);
    }
}
