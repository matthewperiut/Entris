package com.matthewperiut.entris.fabric;

import com.matthewperiut.entris.EntrisClient;
import com.matthewperiut.entris.network.client.HandleAllowEntrisPayload;
import com.matthewperiut.entris.network.client.HandleConfigEntrisPayload;
import com.matthewperiut.entris.network.client.HandleValidEntrisScorePayload;
import com.matthewperiut.entris.network.payload.AllowEntrisPayload;
import com.matthewperiut.entris.network.payload.ConfigEntrisPayload;
import com.matthewperiut.entris.network.payload.ValidEntrisScorePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class EntrisClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ConfigEntrisPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                HandleConfigEntrisPayload.handle(payload.pointsPerEnchant(), payload.secondsPerLevel(), payload.allowNormalEnchanting());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(AllowEntrisPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                HandleAllowEntrisPayload.handle(payload.allow());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(ValidEntrisScorePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                HandleValidEntrisScorePayload.handle(payload.score());
            });
        });

        EntrisClient.init();
        EntrisClient.leftTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.leftTetris);
        EntrisClient.rightTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.rightTetris);
        EntrisClient.downTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.downTetris);
        EntrisClient.upTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.upTetris);
        EntrisClient.holdTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.holdTetris);
        EntrisClient.hardDropTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.hardDropTetris);
        EntrisClient.rightRotateTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.rightRotateTetris);
        EntrisClient.leftRotateTetris = KeyBindingHelper.registerKeyBinding(EntrisClient.leftRotateTetris);
    }
}
