package com.matthewperiut.entris;

import com.matthewperiut.entris.network.payload.FinishEntrisPayload;
import com.matthewperiut.entris.network.payload.RequestEntrisEnchantsPayload;
import com.matthewperiut.entris.network.payload.RequestStartEntrisPayload;
import com.matthewperiut.entris.network.server.HandleFinishEntrisPayload;
import com.matthewperiut.entris.network.server.HandleRequestEntrisEnchantsPayload;
import com.matthewperiut.entris.network.server.HandleRequestStartEntrisPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class Entris {
    public static boolean disableRegularEnchanting = true;

    public static final int MARGIN_OF_ERROR_TIME = 10;

    public static class PlayerData {
        public long timeStamp;
        public int allottedTime;
        public int score = 0;

        public PlayerData(long timeStamp, int allottedTime) {
            this.timeStamp = timeStamp;
            this.allottedTime = allottedTime;
        }
    }
    public static HashMap<PlayerEntity, PlayerData> playerDataMap = new HashMap<>();

    public static final String MOD_ID = "entris";

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(RequestStartEntrisPayload.TYPE, (payload, player, responseSender) -> {
            player.getServer().execute(() -> {
                HandleRequestStartEntrisPayload.handle(player, payload.levels());
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(FinishEntrisPayload.TYPE, (payload, player, responseSender) -> {
            player.getServer().execute(() -> {
                HandleFinishEntrisPayload.handle(player, payload.score());
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(RequestEntrisEnchantsPayload.TYPE, (payload, player, responseSender) -> {
            player.getServer().execute(() -> {
                HandleRequestEntrisEnchantsPayload.handle(player.getServer(), player, payload.enchants());
            });
        });
    }
}
