package com.c446.ironbound_core.events;

import com.c446.ironbound_core.data.attachements.StatusAttachement;
import com.c446.ironbound_core.data.attachements.StatusIncreasedEvent;
import com.c446.ironbound_core.data.attachements.StatusTypes;
import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.ironbound_classes.ClassInstance;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneIBSubClass;
import com.c446.ironbound_core.items.GenericPotion;
import com.c446.ironbound_core.registries.AttachmentReg;
import com.c446.ironbound_core.registries.EffectRegistries;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Objects;

import static com.c446.ironbound_core.data.attachements.StatusTypes.*;
import static com.c446.ironbound_core.registries.AttachmentReg.STATUS_DATA;
import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.ENDER;
import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.HOLY;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void castingBuildupEvent(SpellOnCastEvent event) {
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
    public static void onStatusIncrease(StatusIncreasedEvent.Pre event) {
        if (!event.entity.hasData(STATUS_DATA)) {
            event.entity.setData(STATUS_DATA, new StatusAttachement());
        }
        event.entity.getData(STATUS_DATA).addTo(event.status, event.amount);
        if (event.entity.getData(STATUS_DATA).getMaxFromType(event.entity, event.status) < event.entity.getData(STATUS_DATA).getCurrentFromType(event.status)) {
            StatusAttachement.handleEffect(event.status, event.entity);
        }
    }

    @SubscribeEvent
    public static void onFinishItem(LivingEntityUseItemEvent.Finish event) {
        // LEVEL UP BEHAVIOR
        if (event.getItem().getItem() instanceof GenericPotion potion) {
            var data = event.getEntity().getData(AttachmentReg.LEVEL_DATA);
            if (data.containsClass(potion.ibClass)) {
                data.instances.forEach(classes -> {
                    if (classes.getLevel() == potion.level && classes.classID.equals(potion.ibClass.classId)) {
                        classes.setLevel(potion.level);
                    }
                });
            }
            else {
                data.instances.add(new ClassInstance(potion.ibClass.classId, NoneIBSubClass.instance.subClassID, 1));
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerJoins(PlayerEvent.PlayerLoggedInEvent event) {
        ClassHelper.setPlayerAttribute(event.getEntity());
    }

    @SubscribeEvent
    public static void tryDampenEntity(LivingBreatheEvent event) {
        if (!event.canBreathe()) {
            event.getEntity().forceAddEffect(new MobEffectInstance(EffectRegistries.DAMP, 10, 0), event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerRain(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living && event.getEntity().level() instanceof ServerLevel serverLevel && serverLevel.isRainingAt(event.getEntity().getOnPos())) {
            living.addEffect(new MobEffectInstance(EffectRegistries.DAMP, 5, 0));
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        event.getEntity().setData(
                AttachmentReg.LEVEL_DATA,
                event.getOriginal()
                        .getData(AttachmentReg.LEVEL_DATA)
        );

        ClassHelper.setPlayerAttribute(event.getEntity());
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
