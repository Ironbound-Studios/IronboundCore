package org.ironbound.ironbound_core.ironbound_classes.sub_classes.nature;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.IBSubClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.RogueClass;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.MAX_MANA;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.NATURE_SPELL_POWER;

public class PlagueMaster extends IBSubClass {
    public static final PlagueMaster instance = new PlagueMaster(Ironbound.prefix("sub_class_nature_rogue"), SchoolRegistry.NATURE_RESOURCE, RogueClass.instance);

    static {
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("nature_rogue_mana"), 7.5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(NATURE_SPELL_POWER, new AttributeModifier(Ironbound.prefix("nature_rogue_nature_power"), 0.00625, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public PlagueMaster(ResourceLocation subClassID, ResourceLocation type, IBClass... parents) {
        super(subClassID, type, parents);
    }

    public PlagueMaster(ResourceLocation subClassID, ResourceLocation type, IBClass parent) {
        super(subClassID, type, parent);
    }

    @Override
    public ResourceLocation getResource() {
        return instance.subClassID;
    }
}
