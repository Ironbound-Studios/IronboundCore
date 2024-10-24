package com.c446.ironbound_core.ironbound_classes.sub_classes.fighter;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.SubClasses;

import static com.c446.ironbound_core.registries.AttributeRegistry.FOCUS;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

import com.c446.ironbound_core.ironbound_classes.main_classes.FighterClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.HunterClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class EldritchKnight extends SubClasses {
    public static final EldritchKnight instance = new EldritchKnight(Ironbound.prefix("sub_class_eldritch_knight"), FighterClass.instance, HunterClass.instance);
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("eldritch_knight_mana"), 15, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ELDRITCH_SPELL_POWER, new AttributeModifier(Ironbound.prefix("eldritch_knight_eldr_power"), 0.0125, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(FOCUS, new AttributeModifier(Ironbound.prefix("eldritch_knight_focus"), 0.0125, AttributeModifier.Operation.ADD_VALUE));
    }

    public EldritchKnight(ResourceLocation subClassID, IBClass... parents) {
        super(subClassID, parents);
    }

    public EldritchKnight(IBClass parent, ResourceLocation subClassID) {
        super(parent, subClassID);
    }


}
