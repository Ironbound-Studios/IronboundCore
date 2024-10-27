package com.c446.ironbound_core.items.classes;

import com.c446.ironbound_core.ironbound_classes.ClassInstance;
import com.c446.ironbound_core.ironbound_classes.main_classes.WizardClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import com.c446.ironbound_core.items.ClassItem;
import com.c446.ironbound_core.registries.ComponentRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class WizardItem extends ClassItem {
    public WizardItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!(stack.has(ComponentRegistry.CLASS_COMPONENT.get()))) {

            stack.set(ComponentRegistry.CLASS_COMPONENT.value(), new ClassInstance(WizardClass.instance.classId.toString(), NoneSubClass.instance.subClassID.toString(), 1));

        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {




        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
