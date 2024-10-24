package com.c446.ironbound_core.events;

import com.c446.ironbound_core.data.attachements.StatusTypes;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;

import java.util.ArrayList;

public abstract class MobStatusTriggeredEvent extends net.neoforged.bus.api.Event {
    public LivingEntity entity;
    public ArrayList<com.c446.ironbound_core.data.attachements.StatusTypes> statusList;

    public MobStatusTriggeredEvent(LivingEntity thing, ArrayList<StatusTypes> statuses) {
        this.statusList = statuses;
        this.entity = thing;
    }

    public static class Pre extends MobStatusTriggeredEvent implements ICancellableEvent {
        public Pre(LivingEntity thing, ArrayList<StatusTypes> statuses) {
            super(thing, statuses);
        }


    }

    public static class Post extends MobStatusTriggeredEvent {
        public Post(LivingEntity p, ArrayList<StatusTypes> statuses) {
            super(p, statuses);
        }
    }
}

