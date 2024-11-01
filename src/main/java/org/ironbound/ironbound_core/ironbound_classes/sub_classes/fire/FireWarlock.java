package org.ironbound.ironbound_core.ironbound_classes.sub_classes.fire;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.WarlockClass;

import java.util.List;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.FIRE_SPELL_POWER;

public class FireWarlock extends org.ironbound.ironbound_core.ironbound_classes.IBSubClass {
    public static final FireWarlock instance = new FireWarlock(Ironbound.prefix("fire_warlock"), SchoolRegistry.FIRE_RESOURCE, WarlockClass.instance);


    static {
        instance.addAttribute(ALObjects.Attributes.FIRE_DAMAGE, new AttributeModifier(Ironbound.prefix("fire_warlock_mana"), -0.05, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(FIRE_SPELL_POWER, new AttributeModifier(Ironbound.prefix("fire_warlock_power"), 0.025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public FireWarlock(ResourceLocation subClassID, ResourceLocation school, IBClass... parents) {
        super(subClassID, school, parents);
    }

    public FireWarlock(ResourceLocation subClassID, ResourceLocation school, IBClass parent) {
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