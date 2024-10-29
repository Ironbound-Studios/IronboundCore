package com.c446.ironbound_core.ironbound_classes.sub_classes.ender;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.FighterClass;
import com.c446.ironbound_core.ironbound_classes.main_classes.HunterClass;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.ArrayList;
import java.util.List;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class ShadowStalker extends IBSubClass {
    public static final ShadowStalker instance = new ShadowStalker(Ironbound.prefix("sub_class_ender_rogue"), SchoolRegistry.ENDER_RESOURCE, FighterClass.instance, HunterClass.instance);

    static {
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("ender_rogue_mana"), 7.5, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(ENDER_SPELL_POWER, new AttributeModifier(Ironbound.prefix("ender_rogue_ender_power"), 0.00625, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        instance.addAttribute(ALObjects.Attributes.DODGE_CHANCE, new AttributeModifier(Ironbound.prefix("ender_rogue_dodge"), 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    public List<Component> getClassPerks(int level) {
        ArrayList<Component> list = new ArrayList<>();
        if (level >= 3) {
            list.add(Component.translatable(instance.subClassID.getPath() + ".ability.1"));
        }
        if (level >= 9) {
            list.add(Component.translatable(instance.subClassID.getPath() + ".ability.2"));
        }
        if (level >= 15) {
            list.add(Component.translatable(instance.subClassID.getPath() + ".ability.3"));
        }
        return list;
    }

    public ShadowStalker(ResourceLocation subClassID, ResourceLocation type, IBClass... parents) {
        super(subClassID,type, parents);
    }

    public ShadowStalker(IBClass parent, ResourceLocation type, ResourceLocation subClassID) {
        super(subClassID, type, parent);
    }

    @Override
    public ResourceLocation getResource() {
        return this.subClassID;
    }


}

