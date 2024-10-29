package com.c446.ironbound_core.ironbound_classes.sub_classes.eldritch;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.WizardClass;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.List;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class TimeWizard extends IBSubClass {
    public static final TimeWizard instance = new TimeWizard(Ironbound.prefix("astrologer_class"), SchoolRegistry.ELDRITCH_RESOURCE, WizardClass.instance);


    static {
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("astrologer_mana"), 25, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ELDRITCH_SPELL_POWER, new AttributeModifier(Ironbound.prefix("astrologer_eldritch_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ENDER_SPELL_POWER, new AttributeModifier(Ironbound.prefix("astrologer_ender_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public List<ResourceLocation> getLevelBoostedSpells() {
        return List.of(
                SpellRegistry.SLOW_SPELL.get().getSpellResource(),
                SpellRegistry.HASTE_SPELL.get().getSpellResource(),
                SpellRegistry.EVASION_SPELL.get().getSpellResource(),
                SpellRegistry.TELEPORT_SPELL.get().getSpellResource(),
                ResourceLocation.fromNamespaceAndPath("ironbounds_artefacts", "time_stop")
        );
    }

    public int getLevelBoost(LivingEntity entity){
        return (int) ((ClassHelper.safeGetData(ClassHelper.collectClassItems(entity).getFirst()).level())/10D+1);
    }

    public List<ResourceLocation> getReducedCastTimeSpells() {
        return List.of(
                SpellRegistry.SLOW_SPELL.get().getSpellResource(),
                SpellRegistry.HASTE_SPELL.get().getSpellResource(),
                SpellRegistry.EVASION_SPELL.get().getSpellResource(),
                SpellRegistry.RECALL_SPELL.get().getSpellResource(),
                SpellRegistry.TELEPORT_SPELL.get().getSpellResource(),
                ResourceLocation.fromNamespaceAndPath("ironbounds_artefacts", "time_stop")
        );
    }

    public TimeWizard(ResourceLocation subClassID, ResourceLocation school, IBClass... parents) {
        super(subClassID, school, parents);
    }

    public TimeWizard(ResourceLocation subClassID, ResourceLocation school, IBClass parent) {
        super(subClassID, school, parent);
    }


    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }


}