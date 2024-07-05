package com.matthewperiut.entris.network.payload;

import com.matthewperiut.entris.network.EntrisNetworkingConstants;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class AllowEntrisPayload implements Payload {
    boolean allow;

    public AllowEntrisPayload(boolean allow) {
        this.allow = allow;
    }

    @Override
    public Identifier getId() {
        return EntrisNetworkingConstants.ALLOW_ENTRIS_GAME_PACKET_ID;
    }

    @Override
    public PacketByteBuf getPacket() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(allow);
        return buf;
    }
}
