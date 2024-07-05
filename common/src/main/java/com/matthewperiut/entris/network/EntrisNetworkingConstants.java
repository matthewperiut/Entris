package com.matthewperiut.entris.network;

import net.minecraft.util.Identifier;

public class EntrisNetworkingConstants {
    public static final Identifier START_ENTRIS_GAME_PACKET_ID = new Identifier("entris", "start_entris_game");
    public static final Identifier ALLOW_ENTRIS_GAME_PACKET_ID = new Identifier("entris", "allow_entris_game");
    public static final Identifier FINISH_ENTRIS_GAME_PACKET_ID = new Identifier("entris", "finish_entris_game");
    public static final Identifier VALID_ENTRIS_SCORE_PACKET_ID = new Identifier("entris", "valid_entris_score");
    public static final Identifier REQUEST_ENTRIS_ENCHANTS_PACKET_ID = new Identifier("entris", "request_entris_enchants");
}