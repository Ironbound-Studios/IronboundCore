package org.ironbound.ironbound_core.items;

import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.entity.spells.ChainLightning;
import io.redspace.ironsspellbooks.spells.lightning.ChainLightningSpell;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.ironbound.ironbound_core.ironbound_classes.ClassHelper;
import org.ironbound.ironbound_core.ironbound_classes.ClassInstance;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.NoneClass;
import org.ironbound.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import org.ironbound.ironbound_core.registries.IBClassRegistry;
import org.ironbound.ironbound_core.registries.IBComponentRegistry;
import org.ironbound.ironbound_core.registries.IBSubClassRegistry;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;

import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.GENERIC_DATA;

public class ClassItem extends Item implements ICurioItem {
    public final IBClass ibClass;

    public ClassItem(Properties properties, IBClass ibClass) {
        super(properties);
        this.ibClass = ibClass;
    }

    public static ClassInstance getClassComponent(ItemStack stack) {
        if (stack.has(IBComponentRegistry.CLASS_COMPONENT)) {
            return stack.get(IBComponentRegistry.CLASS_COMPONENT);
        } else {
            return new ClassInstance(NoneClass.instance.classId.toString(), NoneSubClass.instance.subClassID.toString(), 0);
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().hasData(GENERIC_DATA)) {
            slotContext.entity().getData(GENERIC_DATA).immortalityCooldown -= 1;

        }

        ICurioItem.super.curioTick(slotContext, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!(stack.has(IBComponentRegistry.CLASS_COMPONENT.get()))) {
            stack.set(IBComponentRegistry.CLASS_COMPONENT.value(), new ClassInstance(this.ibClass.classId.toString(), NoneSubClass.instance.subClassID.toString(), 1));
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        var map = ICurioItem.defaultInstance.getAttributeModifiers(slotContext, id);
        var component = ClassHelper.safeGetData(stack);
        var mainClass = IBClassRegistry.getMainFromLoc(ResourceLocation.parse(component != null ? component.classID() : NoneClass.instance.classId.toString()));
        var subClass = IBSubClassRegistry.getSubFromLoc(ResourceLocation.parse(component != null ? component.subClassID() : NoneSubClass.instance.subClassID.toString()));
        var level = component != null ? component.level() : 0;
        if (!(mainClass instanceof NoneClass)) {
            map.putAll(mainClass.getAttributesForLevel(level));
        }
        if (!(subClass instanceof NoneSubClass)) {
            map.putAll(subClass.getAttributesForLevel(level));
        }
        return map;
    }

    public ArrayList<Component> getClassTooltip() {
        return new ArrayList<>();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        var value = ClassHelper.safeGetData(stack);
        tooltipComponents.add(Component.translatable("classes.ironbound_core." + ResourceLocation.parse(value.classID()).getPath(), value.level()).withStyle(ChatFormatting.GOLD));
        if (tooltipFlag.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("classes.ironbound_core." + ResourceLocation.parse(value.classID()).getPath() + "_name").withStyle(ChatFormatting.DARK_PURPLE).append(Component.translatable("classes.ironbound_core.class_ability").withStyle(ChatFormatting.DARK_PURPLE)));
            tooltipComponents.addAll(IBClassRegistry.getMainFromLoc(value.classID()).getSubClassPerks(value.level()));
            if (ClassHelper.hasSubClass(stack)) {

                tooltipComponents.add(Component.translatable("classes.ironbound_core." + ResourceLocation.parse(value.subClassID()).getPath() + "_name").withStyle(ChatFormatting.DARK_PURPLE).append(Component.translatable("classes.ironbound_core.sub_class_ability").withStyle(ChatFormatting.DARK_PURPLE)));
                tooltipComponents.addAll(IBSubClassRegistry.getSubFromLoc(value.subClassID()).getClassPerks(value.level()));

            }
        } else {
            tooltipComponents.add(Component.translatable("tooltip.ironbound.hold_shift").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}



