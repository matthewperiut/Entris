package com.matthewperiut.entris.forge;

import com.matthewperiut.entris.Entris;
import com.matthewperiut.entris.EntrisClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(Entris.MOD_ID)
public class EntrisForge {
    public EntrisForge() {
        Entris.init();
    }

    @Mod.EventBusSubscriber(modid = Entris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntrisClient.init();
        }
    }
}
