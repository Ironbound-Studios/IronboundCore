package com.c446.ironbound_core.ironbound_classes.sub_classes.rogue;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClasses;
import com.c446.ironbound_core.ironbound_classes.main_classes.FighterClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.HunterClass;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class ShadowStalker extends IBSubClasses {
    public static final ShadowStalker instance = new ShadowStalker(Ironbound.prefix("sub_class_ender_rogue"), FighterClass.instance, HunterClass.instance);
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("ender_rogue_mana"), 15, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ENDER_SPELL_POWER, new AttributeModifier(Ironbound.prefix("ender_rogue_ender_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ALObjects.Attributes.DODGE_CHANCE, new AttributeModifier(Ironbound.prefix("ender_rogue_dodge"), 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public ShadowStalker(ResourceLocation subClassID, IBClass... parents) {
        super(subClassID, parents);
    }

    public ShadowStalker(IBClass parent, ResourceLocation subClassID) {
        super(parent, subClassID);
    }


}

