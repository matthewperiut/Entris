package com.matthewperiut.entris.neoforge;

import com.matthewperiut.entris.Entris;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Entris.MOD_ID)
public class EntrisNeoForge {
    public EntrisNeoForge(IEventBus modEventBus, ModContainer modContainer)
    {
        Entris.init();
    }

}
