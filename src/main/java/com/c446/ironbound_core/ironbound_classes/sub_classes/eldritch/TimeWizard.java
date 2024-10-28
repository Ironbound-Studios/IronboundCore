package com.c446.ironbound_core.ironbound_classes.sub_classes.eldritch;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.FighterClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.HunterClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.WizardClass;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static com.c446.ironbound_core.registries.AttributeRegistry.FOCUS;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class TimeWizard extends IBSubClass {
    public static final TimeWizard instance = new TimeWizard(Ironbound.prefix("astrologer_class"), SchoolRegistry.ELDRITCH_RESOURCE, WizardClass.instance);
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("astrologer_mana"), 25, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ELDRITCH_SPELL_POWER, new AttributeModifier(Ironbound.prefix("astrologer_eldritch_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ENDER_SPELL_POWER, new AttributeModifier(Ironbound.prefix("astrologer_ender_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
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