package com.matthewperiut.entris;

import com.matthewperiut.entris.network.client.HandleAllowEntrisPayload;
import com.matthewperiut.entris.network.client.HandleValidEntrisScorePayload;
import com.matthewperiut.entris.network.payload.AllowEntrisPayload;
import com.matthewperiut.entris.network.payload.ValidEntrisScorePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class EntrisClient {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(AllowEntrisPayload.TYPE, (payload, player, responseSender) -> {
            MinecraftClient.getInstance().execute(() -> {
                HandleAllowEntrisPayload.handle(payload.allow());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(ValidEntrisScorePayload.TYPE, (payload, player, responseSender) -> {
            MinecraftClient.getInstance().execute(() -> {
                HandleValidEntrisScorePayload.handle(payload.score());
            });
        });
    }
}
