package com.matthewperiut.entris.fabric;

import com.matthewperiut.entris.Entris;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.matthewperiut.entris.game.SoundHelper.*;
import static com.matthewperiut.entris.game.SoundHelper.TICK_SOUND_EVENT;

public class EntrisFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Entris.init();

        Registry.register(Registries.SOUND_EVENT, MOVE_SOUND_ID, MOVE_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, HARD_DROP_SOUND_ID, HARD_DROP_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, FINALIZE_SOUND_ID, FINALIZE_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, ROTATE_SOUND_ID, ROTATE_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, SWAP_SOUND_ID, SWAP_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, TICK_SOUND_ID, TICK_SOUND_EVENT);
    }
}
