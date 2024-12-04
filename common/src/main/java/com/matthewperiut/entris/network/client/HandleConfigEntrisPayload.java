
package com.matthewperiut.entris.network.client;

import com.matthewperiut.entris.client.ClientEntrisInterface;
import com.matthewperiut.entris.config.EntrisConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;

public class HandleConfigEntrisPayload {
    public static void handle(int pointsPerEnchant, int secondsPerLevel, boolean allowNormalEnchanting)
    {
        EntrisConfig.serverPointsPerEnchant = pointsPerEnchant;
        EntrisConfig.serverSecondsPerLevel = secondsPerLevel;
        EntrisConfig.serverAllowNormalEnchanting = allowNormalEnchanting;

        if (MinecraftClient.getInstance().currentScreen instanceof EnchantmentScreen enchantmentScreenHandler) {
            ((ClientEntrisInterface)enchantmentScreenHandler).handleVanillaEnchantingAllowance(allowNormalEnchanting);
        }
    }
}
