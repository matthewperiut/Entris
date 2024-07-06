package com.matthewperiut.entris.network.client;

import com.matthewperiut.entris.client.ClientEntrisInterface;
import dev.architectury.networking.NetworkManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.network.PacketByteBuf;

public class HandleValidEntrisScorePayload implements NetworkManager.NetworkReceiver {
    public static void handle(int score) {
        if (MinecraftClient.getInstance().currentScreen instanceof EnchantmentScreen enchantmentScreenHandler) {
            if (score > 0)
                ((ClientEntrisInterface)enchantmentScreenHandler).validifyScore(score);
            else {

            }
        }
    }

    @Override
    public void receive(PacketByteBuf buf, NetworkManager.PacketContext context) {
        handle(buf.getInt(0));
    }
}
