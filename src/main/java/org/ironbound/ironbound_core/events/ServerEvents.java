package org.ironbound.ironbound_core.events;

import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.ironbound.ironbound_core.data.attachements.StatusAttachement;
import org.ironbound.ironbound_core.data.attachements.StatusIncreasedEvent;
import org.ironbound.ironbound_core.ironbound_classes.ClassHelper;
import org.ironbound.ironbound_core.ironbound_classes.ClassInstance;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.PriestClass;
import org.ironbound.ironbound_core.items.GenericPotion;
import org.ironbound.ironbound_core.registries.IBClassRegistry;
import org.ironbound.ironbound_core.registries.IBDamageSourcesReg;
import org.ironbound.ironbound_core.registries.IBItemRegistry;
import org.ironbound.ironbound_core.registries.IBMobEffectRegistry;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.ENDER;
import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.HOLY;
import static io.redspace.ironsspellbooks.damage.ISSDamageTypes.BLOOD_MAGIC;
import static org.ironbound.ironbound_core.data.attachements.StatusTypes.*;
import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.GENERIC_DATA;
import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.STATUS_DATA;
import static org.ironbound.ironbound_core.registries.IBComponentRegistry.CLASS_COMPONENT;

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
        System.out.println(event.entity.getData(STATUS_DATA).getCurrentFromType(event.status));
    }

    @SubscribeEvent
    public static void onItemUse(LivingEntityUseItemEvent.Finish event) {
        if (event.getItem().getItem() instanceof GenericPotion potion) {
            CuriosApi.getCuriosInventory(event.getEntity()).flatMap(inv -> inv.findFirstCurio(stack -> stack.has(CLASS_COMPONENT) && Objects.requireNonNull(stack.get(CLASS_COMPONENT)).level() < 20)).ifPresent(a ->
                    {
                        if (potion.level >= Objects.requireNonNull(a.stack().get(CLASS_COMPONENT)).level() + 1) {
                            a.stack().set(
                                    CLASS_COMPONENT, new ClassInstance(
                                            Objects.requireNonNull(a.stack().get(CLASS_COMPONENT)).classID(),
                                            Objects.requireNonNull(a.stack().get(CLASS_COMPONENT)).subClassID(),
                                            1 + Objects.requireNonNull(a.stack().get(CLASS_COMPONENT)).level()
                                    )
                            );
                        }
                    }
            );
        }
    }

    @SubscribeEvent
    public static void tryDampenEntity(LivingBreatheEvent event) {
        if (!event.canBreathe()) {
            event.getEntity().forceAddEffect(new MobEffectInstance(IBMobEffectRegistry.DAMP, 10, 0), event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerRain(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living && event.getEntity().level() instanceof ServerLevel serverLevel && serverLevel.isRainingAt(event.getEntity().getOnPos())) {
            living.addEffect(new MobEffectInstance(IBMobEffectRegistry.DAMP, 5, 0));
        }
    }

    @SubscribeEvent
    public static void healEvent(LivingHealEvent event){
        if (event.getEntity() instanceof Player player && ClassHelper.isClass(player, PriestClass.instance)){
            event.setAmount(event.getAmount()*1.5F);
        }
    }

    @SubscribeEvent
    public static void damageEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().hasData(STATUS_DATA)) {
            event.getEntity().setData(STATUS_DATA, new StatusAttachement());
        }

        if (event.getSource().getEntity() instanceof Player player && ClassHelper.isClass((player), PriestClass.instance) && event.getEntity().getType().equals(EntityType.ZOMBIE)){
            event.setNewDamage(event.getOriginalDamage() * (1+ClassHelper.getLevel(player)/20F));
        }

        if (event.getSource().is(Tags.DamageTypes.IS_PHYSICAL) || event.getSource().typeHolder() == IBDamageSourcesReg.getFromKey(event.getEntity(), BLOOD_MAGIC)) {

            var entity = event.getEntity();

            var newData = entity.getData(STATUS_DATA);
            NeoForge.EVENT_BUS.post(new StatusIncreasedEvent(entity, BLEED, (int) (newData.getBleedCurrent() + event.getNewDamage())));

            entity.setData(STATUS_DATA, newData);
        } else if (Objects.equals(event.getSource().typeHolder(), IBDamageSourcesReg.getFromKey(event.getEntity(), BLOOD_MAGIC))) {
            event.getEntity().getActiveEffects().forEach(a -> {
                if (a.getEffect().equals(MobEffects.POISON)) {
                    StatusAttachement.increaseEffectAmpAndLength(IBMobEffectRegistry.ROT, event.getEntity());
                }
            });
        }
    }

    @SubscribeEvent
    public static void onServerStart(ServerStartedEvent event) {
        ServerConfigs.IMBUE_WHITELIST_ITEMS.add(IBItemRegistry.WARLOCK_CURIO.get());
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(GENERIC_DATA)) {
            event.getEntity().getData(GENERIC_DATA).read_book = event.getOriginal().getData(GENERIC_DATA).read_book;
        }
    }

    @SubscribeEvent
    public static void onReadBook(LivingEntityUseItemEvent.Start event) {
        if (ClassHelper.isClass(event.getEntity(), IBClassRegistry.WIZARD_CLASS.get()) && event.getEntity() instanceof Player player) {
            player.getData(GENERIC_DATA).addBook(event.getItem());
        }
    }



}
