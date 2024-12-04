package com.matthewperiut.entris.network.payload;

import com.matthewperiut.entris.network.EntrisNetworkingConstants;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record ConfigEntrisPayload(int pointsPerEnchant, int secondsPerLevel, boolean allowNormalEnchanting) implements CustomPayload {
    public static final PacketCodec<ByteBuf, Integer> INT_CODEC = new PacketCodec<>() {
        @Override
        public Integer decode(ByteBuf byteBuf) {
            return byteBuf.readInt();
        }

        @Override
        public void encode(ByteBuf byteBuf, Integer value) {
            byteBuf.writeInt(value);
        }
    };

    public static final PacketCodec<ByteBuf, Boolean> BOOLEAN_CODEC = new PacketCodec<>() {
        @Override
        public Boolean decode(ByteBuf byteBuf) {
            return byteBuf.readBoolean();
        }

        @Override
        public void encode(ByteBuf byteBuf, Boolean value) {
            byteBuf.writeBoolean(value);
        }
    };

    public static final PacketCodec<ByteBuf, ConfigEntrisPayload> PACKET_CODEC = new PacketCodec<>() {
        @Override
        public ConfigEntrisPayload decode(ByteBuf byteBuf) {
            int pointsPerEnchant = INT_CODEC.decode(byteBuf);
            int secondsPerLevel = INT_CODEC.decode(byteBuf);
            boolean allowNormalEnchanting = BOOLEAN_CODEC.decode(byteBuf);
            return new ConfigEntrisPayload(pointsPerEnchant, secondsPerLevel, allowNormalEnchanting);
        }

        @Override
        public void encode(ByteBuf byteBuf, ConfigEntrisPayload payload) {
            INT_CODEC.encode(byteBuf, payload.pointsPerEnchant());
            INT_CODEC.encode(byteBuf, payload.secondsPerLevel());
            BOOLEAN_CODEC.encode(byteBuf, payload.allowNormalEnchanting());
        }
    };

    public static final Id<ConfigEntrisPayload> ID = new Id<>(EntrisNetworkingConstants.CONFIG_ENTRIS_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, ConfigEntrisPayload> CODEC =
            PacketCodec.tuple(
                    INT_CODEC, ConfigEntrisPayload::pointsPerEnchant,
                    INT_CODEC, ConfigEntrisPayload::secondsPerLevel,
                    BOOLEAN_CODEC, ConfigEntrisPayload::allowNormalEnchanting,
                    ConfigEntrisPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
