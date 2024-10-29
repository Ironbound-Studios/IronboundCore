package com.c446.ironbound_core.items;

import com.c446.ironbound_core.ironbound_classes.ClassHelper;
import com.c446.ironbound_core.ironbound_classes.IBSubClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import com.c446.ironbound_core.registries.IBClassRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SubClassPotion extends Item {



    private final ArrayList<IBSubClass> subClasses = new ArrayList<>();

    public SubClassPotion(Properties properties, IBSubClass... subClasses) {
        super(properties);

        this.subClasses.addAll(List.of(subClasses));
    }

    public ArrayList<IBSubClass> getSubClasses() {
        return subClasses;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ClassHelper.collectClassItems(player).forEach(a -> {




            for (IBSubClass subClass : this.subClasses) {
                if (subClass.parents.contains(IBClassRegistry.getMainFromLoc(ClassHelper.safeGetData(a).classID())) && ClassHelper.safeGetData(a).subClassID().equals(NoneSubClass.instance.subClassID.toString())) {

                    ClassHelper.setSubClass(a, subClass);
                }
            }





        });
        return super.use(level, player, usedHand);
    }
}
