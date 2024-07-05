package com.matthewperiut.entris.network.client;

import com.matthewperiut.entris.client.ClientEntrisInterface;
import dev.architectury.networking.NetworkManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.network.PacketByteBuf;

public class HandleAllowEntrisPayload implements NetworkManager.NetworkReceiver {
    public static void handle(boolean allow)
    {
        if (MinecraftClient.getInstance().currentScreen instanceof EnchantmentScreen enchantmentScreenHandler) {
            if (allow)
                ((ClientEntrisInterface)enchantmentScreenHandler).beginGame();
            else
                ((ClientEntrisInterface)enchantmentScreenHandler).errorHandling();
        }
    }

    @Override
    public void receive(PacketByteBuf buf, NetworkManager.PacketContext context) {
        handle(buf.getBoolean(0));
    }
}
