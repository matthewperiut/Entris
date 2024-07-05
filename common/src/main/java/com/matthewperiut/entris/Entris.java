package com.matthewperiut.entris;

import com.matthewperiut.entris.network.EntrisNetworkingConstants;
import com.matthewperiut.entris.network.client.HandleAllowEntrisPayload;
import com.matthewperiut.entris.network.client.HandleValidEntrisScorePayload;
import com.matthewperiut.entris.network.server.HandleFinishEntrisPayload;
import com.matthewperiut.entris.network.server.HandleRequestEntrisEnchantsPayload;
import com.matthewperiut.entris.network.server.HandleRequestStartEntrisPayload;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.HashMap;
import java.util.Queue;

import static com.matthewperiut.entris.game.SoundHelper.*;

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
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, EntrisNetworkingConstants.START_ENTRIS_GAME_PACKET_ID, new HandleRequestStartEntrisPayload());
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, EntrisNetworkingConstants.ALLOW_ENTRIS_GAME_PACKET_ID, new HandleAllowEntrisPayload());
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, EntrisNetworkingConstants.FINISH_ENTRIS_GAME_PACKET_ID, new HandleFinishEntrisPayload());
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, EntrisNetworkingConstants.VALID_ENTRIS_SCORE_PACKET_ID, new HandleValidEntrisScorePayload());
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, EntrisNetworkingConstants.REQUEST_ENTRIS_ENCHANTS_PACKET_ID, new HandleRequestEntrisEnchantsPayload());
    }
}