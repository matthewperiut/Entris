package com.matthewperiut.entris.mixin;

import com.matthewperiut.entris.Entris;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {
    @Inject(method = "onButtonClick", at = @At("HEAD"), cancellable = true)
    void cancelButtons(PlayerEntity player, int id, CallbackInfoReturnable<Boolean> cir) {
        if (Entris.disableRegularEnchanting )
            cir.setReturnValue(false);
    }
}
