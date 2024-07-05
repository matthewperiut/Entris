package com.matthewperiut.entris.network.payload;

import com.matthewperiut.entris.network.EntrisNetworkingConstants;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class RequestStartEntrisPayload implements Payload {
    int levels;

    public RequestStartEntrisPayload(int levels) {
        this.levels = levels;
    }

    @Override
    public Identifier getId() {
        return EntrisNetworkingConstants.START_ENTRIS_GAME_PACKET_ID;
    }

    @Override
    public PacketByteBuf getPacket() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(levels);
        return buf;
    }
}
