package org.ironbound.ironbound_core.ironbound_classes.sub_classes.evocation;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.WarlockClass;

import java.util.List;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class Evoker extends org.ironbound.ironbound_core.ironbound_classes.IBSubClass {
    public static final Evoker instance = new Evoker(Ironbound.prefix("fire_warlock"), SchoolRegistry.FIRE_RESOURCE, WarlockClass.instance);


    static {
        instance.addAttribute(EVOCATION_SPELL_POWER, new AttributeModifier(Ironbound.prefix("evocation_wizard_power"), -0.05, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(SUMMON_DAMAGE, new AttributeModifier(Ironbound.prefix("evocation_wizard_summon_damage"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public Evoker(ResourceLocation subClassID, ResourceLocation school, IBClass... parents) {
        super(subClassID, school, parents);
    }

    public Evoker(ResourceLocation subClassID, ResourceLocation school, IBClass parent) {
        super(subClassID, school, parent);
    }

    @Override
    public List<ResourceLocation> getInnateSpells() {
        return List.of(
                SpellRegistry.FIREBOLT_SPELL.get().getSpellResource(),
                SpellRegistry.MAGMA_BOMB_SPELL.get().getSpellResource(),
                SpellRegistry.FIRE_BREATH_SPELL.get().getSpellResource()
        );
    }

    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }


}