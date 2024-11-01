package org.ironbound.ironbound_core.data.attachements;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class StatusIncreasedEvent extends Event {
    public LivingEntity entity;
    public StatusTypes status;
    public int amount;

    public StatusIncreasedEvent(LivingEntity thing, StatusTypes statuses, int amount) {
        this.status = statuses;
        this.entity = thing;
        this.amount = amount;
    }


    public static class Pre extends StatusIncreasedEvent implements ICancellableEvent {
        public Pre(LivingEntity thing, StatusTypes statuses, int amount) {
            super(thing, statuses, amount);
        }


    }

    public static class Post extends StatusIncreasedEvent {
        public Post(LivingEntity p, StatusTypes statuses, int amount) {
            super(p, statuses, amount);
        }
    }
}




