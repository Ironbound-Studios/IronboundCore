package com.c446.ironbound_core.ironbound_classes.sub_classes.rogue;

import com.c446.ironbound_core.Ironbound;
import com.c446.ironbound_core.ironbound_classes.IBClass;
import com.c446.ironbound_core.ironbound_classes.IBSubClasses;
import com.c446.ironbound_core.ironbound_classes.main_classes.RogueClass;
import com.c446.ironbound_core.registries.AttachmentReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;

public class PlagueMaster extends IBSubClasses {
    public static final PlagueMaster instance = new PlagueMaster(Ironbound.prefix("sub_class_nature_rogue"), RogueClass.instance);
    static{
        instance.addAttribute(MAX_MANA, new AttributeModifier(Ironbound.prefix("nature_rogue_mana"), 15, AttributeModifier.Operation.ADD_VALUE));
        instance.addAttribute(NATURE_SPELL_POWER, new AttributeModifier(Ironbound.prefix("nature_rogue_nature_power"), 0.0125, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    public static boolean canUsePoisonHand(Player player){
        if (player.hasData(AttachmentReg.LEVEL_DATA)){
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            player.getData(AttachmentReg.LEVEL_DATA).instances.forEach(a->{
                if (a.subClassID.equals(instance.subClassID)){
                    atomicBoolean.set(true);
                }
            });
            return atomicBoolean.get();
        }
        return false;
    }

    public PlagueMaster(ResourceLocation subClassID, IBClass... parents) {
        super(subClassID, parents);
    }

    public PlagueMaster(IBClass parent, ResourceLocation subClassID) {
        super(parent, subClassID);
    }
}
