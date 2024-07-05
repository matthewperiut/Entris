package com.matthewperiut.entris.network.payload;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public interface Payload {
    Identifier getId();
    PacketByteBuf getPacket();
}
