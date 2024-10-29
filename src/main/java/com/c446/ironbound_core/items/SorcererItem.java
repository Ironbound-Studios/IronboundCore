package com.c446.ironbound_core.items;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class SorcererItem extends ClassItem {
    public SorcererItem(Properties properties, IBClass ibClass) {
        super(properties, ibClass);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        var defaultAttributes = super.getAttributeModifiers(slotContext, id, stack);

        var entity = slotContext.entity();
        if (entity == null) {
            return defaultAttributes;
        }
        HashMultimap<Holder<Attribute>, AttributeModifier> sorcererSpecific = HashMultimap.create();



        defaultAttributes.putAll(sorcererSpecific);
        return defaultAttributes;
    }
}