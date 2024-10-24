package com.c446.ironbound_core.events;

import com.c446.ironbound_core.data.attachements.StatusAttachement;
import com.c446.ironbound_core.data.attachements.StatusIncreasedEvent;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.registries.AttacmentReg;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.c446.ironbound_core.data.attachements.StatusTypes.*;
import static com.c446.ironbound_core.registries.AttacmentReg.STATUS_DATA;
import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.ENDER;
import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.HOLY;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void test(SpellPreCastEvent event) {
        if (!event.getEntity().hasData(STATUS_DATA)) {
            event.getEntity().setData(STATUS_DATA, new StatusAttachement());
        }
        var spell = SpellRegistry.getSpell(event.getSpellId());
        int buildUp = (int) spell.getSpellPower(event.getSpellLevel(), event.getEntity());
        var entity = event.getEntity();

        if (spell.getSchoolType() == SchoolRegistry.ELDRITCH.get()) {
            if (spell.getSpellResource().equals(SpellRegistry.ABYSSAL_SHROUD_SPELL.get().getSpellResource())) {
                buildUp *= 20;
            } else if (Objects.equals(spell.getSpellResource(), ResourceLocation.fromNamespaceAndPath("ironbounds_artefacts", "time_stop"))) {
                buildUp *= 15;
            } else {
                buildUp *= 5;
            }
            NeoForge.EVENT_BUS.post(new StatusIncreasedEvent.Pre(entity, MADNESS, buildUp));
        } else if (spell.getSchoolType().equals(HOLY.get())) {
            NeoForge.EVENT_BUS.post(new StatusIncreasedEvent.Pre(entity, FERVOR, buildUp));
        } else if (spell.getSchoolType().equals(ENDER.get())) {
            NeoForge.EVENT_BUS.post(new StatusIncreasedEvent.Pre(entity, HOLLOW, buildUp));
        }
        System.out.println(buildUp + "  " + event.getSpellId() + " " + event.getEntity().getName().getString() + " " + event.getEntity().getData(STATUS_DATA).getMadnessCurrent());
    }

    @SubscribeEvent
    public static void onStatusIncrease(StatusIncreasedEvent event) {
        if (!event.entity.hasData(STATUS_DATA)) {
            event.entity.setData(STATUS_DATA, new StatusAttachement());
        }
        event.entity.getData(STATUS_DATA).addTo(event.status, event.amount);
        if (event.entity.getData(STATUS_DATA).getMaxFromType(event.entity, event.status) > event.entity.getData(STATUS_DATA).getCurrentFromType(event.status)) {
            StatusAttachement.handleEffect(event.status, event.entity);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        event.getEntity().setData(
                AttacmentReg.LEVEL_DATA,
                event.getOriginal()
                        .getData(AttacmentReg.LEVEL_DATA)
        );
    }

    @SubscribeEvent
    public static void genDataAttachments(LivingDamageEvent.Pre event) {
        if (!event.getEntity().hasData(STATUS_DATA)) {
            event.getEntity().setData(STATUS_DATA, new StatusAttachement());
        }

        if (event.getSource().is(Tags.DamageTypes.IS_PHYSICAL)) {

            var entity = event.getEntity();
            var current = entity.getData(STATUS_DATA).getCurrentFromType(StatusTypes.BLEED);

            var newData = entity.getData(STATUS_DATA);
            NeoForge.EVENT_BUS.post(new StatusIncreasedEvent(entity, BLEED, (int) (newData.getBleedCurrent() + event.getNewDamage())));

            entity.setData(STATUS_DATA, newData);
        }
    }
}