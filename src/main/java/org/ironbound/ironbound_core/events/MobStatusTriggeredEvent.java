package org.ironbound.ironbound_core.events;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;
import org.ironbound.ironbound_core.data.attachements.StatusTypes;

import java.util.ArrayList;

public abstract class MobStatusTriggeredEvent extends net.neoforged.bus.api.Event {
    public LivingEntity entity;
    public ArrayList<StatusTypes> statusList;

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

