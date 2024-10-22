package com.example.examplemod.events;

import com.example.examplemod.capabilities.StatusTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;

import java.util.ArrayList;

public abstract class MobStatusTriggeredEvent<K extends LivingEntity> extends net.neoforged.bus.api.Event {
    public K player;
    public ArrayList<StatusTypes> statusList;

    public MobStatusTriggeredEvent(K player, ArrayList<StatusTypes> statuses) {
        this.statusList = statuses;
        this.player = player;
    }

    public static class Pre<K extends LivingEntity> extends MobStatusTriggeredEvent<K> implements ICancellableEvent {
        public Pre(K p, ArrayList<StatusTypes> statuses) {
            super(p, statuses);
        }


    }

    public static class Post<K extends LivingEntity> extends MobStatusTriggeredEvent<K> {
        public Post(K p, ArrayList<StatusTypes> statuses) {
            super(p, statuses);
        }
    }
}

