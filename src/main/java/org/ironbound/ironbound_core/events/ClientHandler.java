package org.ironbound.ironbound_core.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.EventBusSubscriber;
import org.ironbound.ironbound_core.Ironbound;

@SuppressWarnings("unchecked")
@EventBusSubscriber(value = Dist.CLIENT, modid = Ironbound.MODID, bus = EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientHandler {


}
