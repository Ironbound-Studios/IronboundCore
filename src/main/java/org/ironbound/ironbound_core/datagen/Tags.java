package org.ironbound.ironbound_core.datagen;

import com.c446.ironbound_artefacts.IronboundArtefact;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class Tags {
    public static class SpellTags{
        public static TagKey<AbstractSpell> OFFENSIVE_SPELL = create(IronboundArtefact.prefix("offensive_spell"));
        public static TagKey<AbstractSpell> UTILITY_SPELL = create(IronboundArtefact.prefix("utility_spell"));
        public static TagKey<AbstractSpell> DEFENSIVE_SPELL = create(IronboundArtefact.prefix("defensive_spell"));
        public static TagKey<AbstractSpell> MOUVEMENT_SPELL = create(IronboundArtefact.prefix("mouvement_spell"));

        public static TagKey<AbstractSpell> create(ResourceLocation name){
            return new TagKey<AbstractSpell>(SpellRegistry.SPELL_REGISTRY_KEY, name);
        }

    }


}
