package com.c446.ironbound_core.events;

import com.c446.ironbound_core.data.attachements.StatusAttachement;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.registries.CapabilityRegistry;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.spells.eldritch.AbyssalShroudSpell;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Objects;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void test(SpellPreCastEvent event) {
        if (!event.getEntity().hasData(CapabilityRegistry.STATUS_DATA)) {
            event.getEntity().setData(CapabilityRegistry.STATUS_DATA, new StatusAttachement());
        }

        var spell = SpellRegistry.getSpell(event.getSpellId());
        var buildUp = spell.getSpellPower(event.getSpellLevel(), event.getEntity());

        if (spell.getSchoolType() == SchoolRegistry.ELDRITCH) {
            if (spell instanceof AbyssalShroudSpell) {
                buildUp *= 100;
            } else if (Objects.equals(spell.getSpellResource(), ResourceLocation.fromNamespaceAndPath("ironbounds_artefacts", "time_stop"))) {
                buildUp *= 15;
            } else {
                buildUp *= 5;
            }
        }
        System.out.println(buildUp + event.getSpellId() + event.getEntity().getName().getString());

    }

    @SubscribeEvent
    public static void genDataAttachments(LivingDamageEvent.Pre event) {
        if (!event.getEntity().hasData(CapabilityRegistry.STATUS_DATA)) {
            event.getEntity().setData(CapabilityRegistry.STATUS_DATA, new StatusAttachement());
        }

        if (event.getSource().is(Tags.DamageTypes.IS_PHYSICAL)) {

            var entity = event.getEntity();
            var current = entity.getData(CapabilityRegistry.STATUS_DATA).getCurrentFromType(StatusTypes.BLEED);

            var newData = entity.getData(CapabilityRegistry.STATUS_DATA);
            newData.setBleedCurrent((int) (newData.getBleedCurrent() + event.getNewDamage()));

            entity.setData(CapabilityRegistry.STATUS_DATA, newData);
        }
    }
}
