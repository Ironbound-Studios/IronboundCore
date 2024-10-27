package com.c446.ironbound_core.ironbound_classes;

import org.jetbrains.annotations.NotNull;

public record ClassInstance(@NotNull String classID, @NotNull String subClassID, int level) {


    public ClassInstance(String classID, String  subClassID, int level) {
        this.classID = classID;
        this.subClassID = subClassID;
        this.level = level;
    }

}
