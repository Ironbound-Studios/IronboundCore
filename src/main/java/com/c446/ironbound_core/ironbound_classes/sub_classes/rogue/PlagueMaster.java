package com.c446.ironbound_core.ironbound_classes.sub_classes.rogue;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.RogueClass;
import com.c446.ironbound_core.registries.ClassRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class PlagueMaster extends IBSubClass {
    public static final PlagueMaster instance = new PlagueMaster(Ironbound.prefix("sub_class_nature_rogue"), ClassRegistry.ROGUE_CLASS.get());
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("nature_rogue_mana"), 15, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(NATURE_SPELL_POWER, new AttributeModifier(Ironbound.prefix("nature_rogue_nature_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public PlagueMaster(ResourceLocation subClassID, IBClass... parents) {
        super(subClassID, parents);
    }

    public PlagueMaster(IBClass parent, ResourceLocation subClassID) {
        super(parent, subClassID);
    }

    @Override
    public ResourceLocation getResource() {
        return instance.subClassID;
    }
}
