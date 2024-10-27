package com.c446.ironbound_core.items;

import com.c446.ironbound_core.ironbound_classes.ClassInstance;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.NoneClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import com.c446.ironbound_core.registries.ClassRegistry;
import com.c446.ironbound_core.registries.ComponentRegistry;
import com.c446.ironbound_core.registries.SubClassRegistry;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassItem extends Item implements ICurioItem {
    public ClassItem(Properties properties) {
        super(properties);
    }

    public static ClassInstance getClassComponent(ItemStack stack){
        if (stack.has(ComponentRegistry.CLASS_COMPONENT)){
            return stack.get(ComponentRegistry.CLASS_COMPONENT);
        }
        else {
            return new ClassInstance(NoneClass.instance.classId.toString(), NoneSubClass.instance.subClassID.toString(), 0);
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        var map = ICurioItem.defaultInstance.getAttributeModifiers(slotContext, id);
        var component = getClassComponent(stack);
        var mainClass = ClassRegistry.getMainFromLoc(ResourceLocation.parse(component != null ? component.classID() : NoneClass.instance.classId.toString()));
        var subClass = SubClassRegistry.getSubFromLoc(ResourceLocation.parse(component != null ? component.subClassID() : NoneSubClass.instance.subClassID.toString()));
        var level = component != null ? component.level() : 0;
        if (!(mainClass instanceof NoneClass)){
            map.putAll(mainClass.getAttributesForLevel(level));
        }
        if (!(subClass instanceof NoneSubClass)){
            map.putAll(subClass.getAttributesForLevel(level));;
        }
        return map;
    }

    public ArrayList<Component> getClassTooltip(){
        return new ArrayList<>();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        var value = getClassComponent(stack);
        tooltipComponents.add(Component.translatable("classes.ironbound_core."+ResourceLocation.parse(value.classID()).getPath(), getClassComponent(stack).level()).withStyle(ChatFormatting.GOLD));
        tooltipComponents.addAll(this.getClassTooltip());



        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
