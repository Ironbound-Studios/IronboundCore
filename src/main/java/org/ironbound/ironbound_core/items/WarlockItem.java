package org.ironbound.ironbound_core.items;

import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.api.spells.SpellSlot;
import io.redspace.ironsspellbooks.capabilities.magic.SpellContainer;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.spells.ender.EchoingStrikesSpell;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.ClassHelper;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.registries.IBSubClassRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import static io.redspace.ironsspellbooks.registries.ComponentRegistry.SPELL_CONTAINER;

public class WarlockItem extends ClassItem {
    public WarlockItem(Properties properties, IBClass ibClass) {
        super(properties, ibClass);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);

        var data = ClassHelper.safeGetData(stack);

        var spellList = IBSubClassRegistry.getSubFromLoc(data.subClassID()).getInnateSpells();

        var slots = new SpellSlot[spellList.size()];
        if (spellList.isEmpty()) {
            stack.remove(SPELL_CONTAINER);
            return;
        }
        for (int i = 0; i < spellList.size(); i++) {
            var spellI = SpellRegistry.getSpell(spellList.get(i));
            slots[i] = new SpellSlot(new SpellData(spellI, Math.max(1, (int) ((ClassHelper.getLevel(slotContext.entity()) / 20F) * spellI.getMaxLevel())), true), i);
        }


        var copy = stack.copy();
        if (ClassHelper.getLevel(slotContext.entity()) >= 15) {
            copy.set(ComponentRegistry.SPELL_CONTAINER, new SpellContainer(spellList.size() + 1, true, false, false, slots)
            );
        }
        copy.set(ComponentRegistry.SPELL_CONTAINER, new SpellContainer(spellList.size() + 1, true, false, false, slots));

        CuriosApi.getCuriosInventory(slotContext.entity()).ifPresent(a -> a.setEquippedCurio(slotContext.identifier(), slotContext.index(), copy));
    }


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        var attributes = super.getAttributeModifiers(slotContext, id, stack);
        if (slotContext.entity() != null) {
            if (ClassHelper.isSubClass(slotContext.entity(), IBSubClassRegistry.FIRE_WARLOCK.get())) {
                if (slotContext.entity().isOnFire()) {
                    attributes.put(AttributeRegistry.FIRE_SPELL_POWER, new AttributeModifier(Ironbound.prefix("on_fire_fire_spell_power_bonus"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                }
                if (slotContext.entity().level.dimension() == ServerLevel.NETHER) {
                    attributes.put(AttributeRegistry.SPELL_POWER, new AttributeModifier(Ironbound.prefix("fire_warlock_home"), 0.25, AttributeModifier.Operation.ADD_VALUE));
                }
            }
/*            if (ClassHelper.isSubClass(slotContext.entity(), IBSubClassRegistry.FIRE_WARLOCK.get())) {
                if (slotContext.entity().isOnFire()) {
                    attributes.put(AttributeRegistry.FIRE_SPELL_POWER, new AttributeModifier(Ironbound.prefix("on_fire_fire_spell_power_bonus"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                }
                if (slotContext.entity().level.dimension() == ServerLevel.NETHER) {
                    attributes.put(AttributeRegistry.SPELL_POWER, new AttributeModifier(Ironbound.prefix("fire_warlock_home"), 0.25, AttributeModifier.Operation.ADD_VALUE));
                }
            }*/
        }

        return attributes;
    }
}
