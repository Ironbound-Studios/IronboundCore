package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.ironbound_classes.main_classes.NoneClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import com.c446.ironbound_core.items.ClassItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.c446.ironbound_core.registries.IBComponentRegistry.CLASS_COMPONENT;

public abstract class ClassHelper {

    public static ClassInstance safeGetData(ItemStack stack) {
        return (stack.has(CLASS_COMPONENT)) ? (stack.get(CLASS_COMPONENT)) : (new ClassInstance(NoneClass.instance.classId.toString(), NoneSubClass.instance.subClassID.toString(), 0));
    }

    public static ClassInstance getFirstData(LivingEntity entity) {
        var stack = collectClassItems(entity).getFirst();
        return (stack.has(CLASS_COMPONENT)) ? (stack.get(CLASS_COMPONENT)) : (new ClassInstance(NoneClass.instance.classId.toString(), NoneSubClass.instance.subClassID.toString(), 0));
    }

    public static boolean hasSubClass(ItemStack stack) {
        return !(safeGetData(stack).subClassID().equals(NoneSubClass.instance.subClassID.toString()));
    }

    public static List<ClassInstance> safeGetData(LivingEntity living) {
        ArrayList<ClassInstance> list = new ArrayList<ClassInstance>();
        collectClassItems(living).forEach(a -> list.add(safeGetData(a)));
        return list;
    }

    public static void setSubClass(ItemStack stack, IBSubClass subClasses) {
        if (!safeGetData(stack).classID().equals(NoneClass.instance.classId.toString())) {
            var data = safeGetData(stack);
            stack.set(CLASS_COMPONENT, new ClassInstance(data.classID(), subClasses.getResource().toString(), data.level()));
        }
    }

    public static boolean isClass(LivingEntity living, IBClass ibClass) {
        var bool = new AtomicBoolean(false);
        collectClassItems(living).forEach(a -> {
            if (safeGetData(a).classID().equals(ibClass.classId.getPath())) {
                bool.set(true);
            }
        });
        return bool.get();
    }

    public static boolean isSubClass(LivingEntity living, IBSubClass ibClass) {
        var bool = new AtomicBoolean(false);
        collectClassItems(living).forEach(a -> {
            if (safeGetData(a).subClassID().equals(ibClass.subClassID.toString())) {
                bool.set(true);
            }
        });
        return bool.get();
    }


    public static ArrayList<ItemStack> collectClassItems(LivingEntity living) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        CuriosApi.getCuriosInventory(living).ifPresent(inv -> inv.findCurios(stack -> {
                    if (stack.getItem() instanceof ClassItem && stack.has(CLASS_COMPONENT)) {
                        stacks.add(stack);
                    }
                    return false;
                })
        );
        return stacks;
    }

    public static int getLevel(LivingEntity entity) {
        return safeGetData(entity).getFirst().level();
    }
}