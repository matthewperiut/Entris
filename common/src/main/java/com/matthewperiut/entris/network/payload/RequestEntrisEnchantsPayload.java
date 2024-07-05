package com.matthewperiut.entris.network.payload;

import com.matthewperiut.entris.network.EntrisNetworkingConstants;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class RequestEntrisEnchantsPayload implements Payload {
    ArrayList<String> enchants;
    public RequestEntrisEnchantsPayload(ArrayList<String> enchants) {
        this.enchants = enchants;
    }

    public static ArrayList<String> read(PacketByteBuf buf) {
        ArrayList<String> enchants = new ArrayList<>();
        int size = buf.readInt(); // Read the size of the list
        for (int i = 0; i < size; i++) {
            enchants.add(buf.readString(100)); // Read each string, max length 32767
        }
        return enchants;
    }

    @Override
    public Identifier getId() {
        // Replace "your_mod_id" with the appropriate mod identifier and "request_enchants" with the appropriate name
        return EntrisNetworkingConstants.REQUEST_ENTRIS_ENCHANTS_PACKET_ID;
    }

    @Override
    public PacketByteBuf getPacket() {
        PacketByteBuf buf = new PacketByteBuf(io.netty.buffer.Unpooled.buffer());
        buf.writeInt(enchants.size());
        for (String enchant : enchants) {
            buf.writeString(enchant);
        }
        return buf;
    }
}
