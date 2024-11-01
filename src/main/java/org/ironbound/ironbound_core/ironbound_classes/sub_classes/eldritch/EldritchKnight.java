package org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.IBSubClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.FighterClass;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.ELDRITCH_SPELL_POWER;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.MAX_MANA;
import static org.ironbound.ironbound_core.registries.IBAttributeRegistry.FOCUS;

public class EldritchKnight extends IBSubClass {
    public static final EldritchKnight instance = new EldritchKnight(Ironbound.prefix("sub_class_eldritch_knight"), SchoolRegistry.ELDRITCH_RESOURCE, FighterClass.instance);

    static {
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("eldritch_knight_mana"), 7.5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ELDRITCH_SPELL_POWER, new AttributeModifier(Ironbound.prefix("eldritch_knight_eldr_power"), 0.00625, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(FOCUS, new AttributeModifier(Ironbound.prefix("eldritch_knight_focus"), 0.0125, AttributeModifier.Operation.ADD_VALUE));
    }

    public EldritchKnight(ResourceLocation subClassID, ResourceLocation school, IBClass... parents) {
        super(subClassID, school, parents);
    }

    public EldritchKnight(ResourceLocation subClassID, ResourceLocation school, IBClass parent) {
        super(subClassID, school, parent);
    }


    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }


}
