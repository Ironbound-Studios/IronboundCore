package com.c446.ironbound_core.ironbound_classes;

import com.c446.ironbound_core.ironbound_classes.main_classes.NoneClass;
import com.c446.ironbound_core.ironbound_classes.sub_classes.NoneSubClass;
import org.jetbrains.annotations.NotNull;

public record ClassInstance(@NotNull String classID, @NotNull String subClassID, int level) {


    public ClassInstance(String classID, String  subClassID, int level) {
        this.classID = classID;
        this.subClassID = subClassID;
        this.level = level;
    }

    public static ClassInstance newEmptyClass(){
        return new ClassInstance(NoneClass.instance.classId.toString(), NoneSubClass.instance.subClassID.toString(), 0);
    }

}
