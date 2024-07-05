package com.matthewperiut.entris.network.payload;

import com.matthewperiut.entris.network.EntrisNetworkingConstants;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ValidEntrisScorePayload implements Payload {
    int score;
    public ValidEntrisScorePayload(int score) {
        this.score = score;
    }

    @Override
    public Identifier getId() {
        return EntrisNetworkingConstants.VALID_ENTRIS_SCORE_PACKET_ID;
    }

    @Override
    public PacketByteBuf getPacket() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(score);
        return buf;
    }
}