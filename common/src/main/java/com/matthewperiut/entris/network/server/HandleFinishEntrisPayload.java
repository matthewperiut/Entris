package com.matthewperiut.entris.network.server;

import com.matthewperiut.entris.Entris;
import com.matthewperiut.entris.network.payload.AllowEntrisPayload;
import com.matthewperiut.entris.network.payload.ValidEntrisScorePayload;
import dev.architectury.networking.NetworkManager;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static com.matthewperiut.entris.Entris.playerDataMap;

public class HandleFinishEntrisPayload implements NetworkManager.NetworkReceiver {
    public static void handle(ServerPlayerEntity player, int score)
    {
        Entris.PlayerData playerTime = playerDataMap.get(player);
        if ((System.currentTimeMillis() - playerTime.timeStamp) / 1000 > playerTime.allottedTime) {
            player.sendMessage(Text.literal("INVALID ENTRIS TIMESTAMP"));

            ValidEntrisScorePayload payload = new ValidEntrisScorePayload(-1);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            playerDataMap.remove(player);
        } else {
            ValidEntrisScorePayload payload = new ValidEntrisScorePayload(score);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            playerDataMap.get(player).score = score;
        }
    }

    @Override
    public void receive(PacketByteBuf buf, NetworkManager.PacketContext context) {
        handle((ServerPlayerEntity) context.getPlayer(), buf.getInt(0));
    }
}
