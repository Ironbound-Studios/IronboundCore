package org.ironbound.ironbound_core.ironbound_classes.sub_classes.eldritch;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.ironbound.ironbound_core.Ironbound;
import org.ironbound.ironbound_core.ironbound_classes.ClassHelper;
import org.ironbound.ironbound_core.ironbound_classes.IBClass;
import org.ironbound.ironbound_core.ironbound_classes.IBSubClass;
import org.ironbound.ironbound_core.ironbound_classes.main_classes.WizardClass;

import java.util.List;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static org.ironbound.ironbound_core.registries.IBAttachmentRegistry.GENERIC_DATA;

public class TimeWizard extends IBSubClass {
    public static final TimeWizard instance = new TimeWizard(Ironbound.prefix("astrologer_class"), SchoolRegistry.ELDRITCH_RESOURCE, WizardClass.instance);


    static {
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("astrologer_mana"), 15, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ELDRITCH_SPELL_POWER, new AttributeModifier(Ironbound.prefix("astrologer_eldritch_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ENDER_SPELL_POWER, new AttributeModifier(Ironbound.prefix("astrologer_ender_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }


    /**
     * @return returns the list of spells that the class will boost. For a Healing Domain Clerc, stuff like Heal, Greater Heal, or Fortification would have been found.
     */
    public List<ResourceLocation> getLevelBoostedSpells() {
        return List.of(
                SpellRegistry.SLOW_SPELL.get().getSpellResource(),
                SpellRegistry.HASTE_SPELL.get().getSpellResource(),
                SpellRegistry.EVASION_SPELL.get().getSpellResource(),
                SpellRegistry.TELEPORT_SPELL.get().getSpellResource(),
                ResourceLocation.fromNamespaceAndPath("ironbounds_artefacts", "time_stop")
        );
    }


    /**
     * @param entity : the entity to get the total boost for
     * @return
     */
    public int getLevelBoost(LivingEntity entity) {
        return (int) (Math.floor(ClassHelper.getLevel(entity) / 10D) + getBookWyrm(entity));
    }

    public double getBookWyrm(LivingEntity entity) {
        if (entity.hasData(GENERIC_DATA)) {
            var data = entity.getData(GENERIC_DATA).read_book.size();
            if (data >= 150) {
                return 2D;
            } else if (data >= 50) {
                return 1D;
            }
        }
        return 0D;
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